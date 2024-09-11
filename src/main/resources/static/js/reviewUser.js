document.addEventListener('DOMContentLoaded', function() {
    const searchButton = document.getElementById('search-button');
    const searchInput = document.getElementById('search-input');
    const searchType = document.getElementById('search-type');
    const viewAllButton = document.getElementById('view-all-button');
    const recentReviews = document.getElementById('recent-reviews');
    const reviewsDisplay = document.getElementById('reviews-display');
    const prevButton = document.getElementById('prev-review');
    const nextButton = document.getElementById('next-review');
    const reviewTitle = document.getElementById('review-title');
    let currentPage = 0;
    const reviewsPerPage = 4;
    let allReviews = [];

    function displayReviews(reviewsToShow, title) {
        reviewTitle.textContent = title;
        reviewsDisplay.innerHTML = '';
        if (reviewsToShow.length === 0) {
            reviewsDisplay.innerHTML = '<p>표시할 리뷰가 없습니다.</p>';
        } else {
            const startIndex = currentPage * reviewsPerPage;
            const endIndex = startIndex + reviewsPerPage;
            const pageReviews = reviewsToShow.slice(startIndex, endIndex);

            pageReviews.forEach(review => {
                const accordionItem = createReviewAccordionItem(review);
                reviewsDisplay.appendChild(accordionItem);
            });
        }
        setupAccordion();
        updateNavigationButtons(reviewsToShow.length);
        updateReviewsDisplayOverflow();
    }

    function createReviewAccordionItem(review) {
        const accordionItem = document.createElement('div');
        accordionItem.className = 'accordion-item';

        let imageHTML = '';
        if (review.fileName) {
            imageHTML = `<img src="/uploads/${review.fileName}" alt="리뷰 이미지" class="review-image">`;
        }

        console.log('Review data:', review);

        const pName = review.pname && review.pname.trim() !== '' ? review.pname : '상품명 미지정';
        const writerId = review.writerId && review.writerId.trim() !== '' ? review.writerId : '작성자 미상';
        const reTitle = review.reTitle && review.reTitle.trim() !== '' ? review.reTitle : '제목 없음';
        const reContent = review.reContent && review.reContent.trim() !== '' ? review.reContent : '내용 없음';

        accordionItem.innerHTML = `
            <div class="accordion-header">
                <span>${pName} - ${writerId}</span>
                <span class="accordion-arrow">▼</span>
            </div>
            <div class="accordion-content">
                <div class="accordion-content-inner">
                    <h3>${reTitle}</h3>
                    <p>${reContent}</p>
                    ${imageHTML}
                </div>
            </div>
        `;
        return accordionItem;
    }

    function setupAccordion() {
        const accordionItems = document.querySelectorAll('.accordion-item');

        accordionItems.forEach(item => {
            const header = item.querySelector('.accordion-header');
            const content = item.querySelector('.accordion-content');
            const arrow = item.querySelector('.accordion-arrow');

            header.addEventListener('click', () => {
                accordionItems.forEach(otherItem => {
                    if (otherItem !== item && otherItem.classList.contains('active')) {
                        otherItem.classList.remove('active');
                        otherItem.querySelector('.accordion-content').style.maxHeight = null;
                        otherItem.querySelector('.accordion-arrow').textContent = '▼';
                    }
                });

                item.classList.toggle('active');
                if (item.classList.contains('active')) {
                    content.style.maxHeight = content.scrollHeight + "px";
                    arrow.textContent = '▲';
                } else {
                    content.style.maxHeight = null;
                    arrow.textContent = '▼';
                }

                updateReviewsDisplayOverflow();
            });
        });
    }

    function updateReviewsDisplayOverflow() {
        const reviewsRight = document.querySelector('.reviews-right');

        if (reviewsDisplay.scrollHeight > reviewsRight.clientHeight) {
            reviewsDisplay.style.overflowY = 'auto';
        } else {
            reviewsDisplay.style.overflowY = 'hidden';
        }
    }

    function updateNavigationButtons(totalReviews) {
        const totalPages = Math.ceil(totalReviews / reviewsPerPage);
        prevButton.disabled = currentPage === 0;
        nextButton.disabled = currentPage === totalPages - 1 || totalReviews <= reviewsPerPage;
    }

    function navigateReviews(direction) {
        if (direction === 'next' && !nextButton.disabled) {
            currentPage++;
        } else if (direction === 'prev' && !prevButton.disabled) {
            currentPage--;
        }
        displayReviews(allReviews, "고객 리뷰");
    }

    function searchReviews() {
        const searchTerm = searchInput.value;
        const searchCategory = searchType.value;
        currentPage = 0;
        fetchReviews(searchCategory, searchTerm);
    }

    function showAllReviews() {
        currentPage = 0;
        fetchReviews();
    }

    const reviewForm = document.querySelector('.review-form form');
    reviewForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const formData = new FormData(this);

        console.log('상품명:', formData.get('pName'));

        fetch('/api/reviews', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Review added:', data);
                displayNewReview(data);
                this.reset();
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });

    function displayNewReview(review) {
        const accordionItem = createReviewAccordionItem(review);
        reviewsDisplay.insertBefore(accordionItem, reviewsDisplay.firstChild);
        setupAccordion();
        allReviews.unshift(review);
        displayReviews(allReviews, "고객 리뷰");
    }

    function fetchReviews(searchType, searchKeyword) {
        let url = '/api/reviews';
        if (searchType && searchKeyword) {
            url += `?searchType=${searchType}&searchKeyword=${searchKeyword}`;
        }

        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log('Received reviews:', data);
                allReviews = data.map(review => {
                    console.log('Processing review:', review);
                    return {
                        ...review,
                        pname: review.pname && review.pname.trim() !== '' ? review.pname : '상품명 미지정',
                        writerId: review.writerId && review.writerId.trim() !== '' ? review.writerId : '작성자 미상',
                        reTitle: review.reTitle && review.reTitle.trim() !== '' ? review.reTitle : '제목 없음',
                        reContent: review.reContent && review.reContent.trim() !== '' ? review.reContent : '내용 없음'
                    };
                });
                displayReviews(allReviews, searchType && searchKeyword ? "검색 결과" : "고객 리뷰");
            })
            .catch(error => console.error('Error:', error));
    }

    searchButton.addEventListener('click', searchReviews);
    viewAllButton.addEventListener('click', showAllReviews);
    prevButton.addEventListener('click', () => navigateReviews('prev'));
    nextButton.addEventListener('click', () => navigateReviews('next'));

    fetchReviews();
});