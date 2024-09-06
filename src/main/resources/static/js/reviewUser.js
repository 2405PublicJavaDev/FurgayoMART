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

    // 가상의 리뷰 데이터 (이미지 URL 추가)
    let reviews = [
        { id: 1, productName: "사과", content: "제품도 신선하고 품질도 아주 좋아요", author: "고객1", imageUrl: "path/to/image1.jpg" },
        { id: 2, productName: "배", content: "결제가 간편해서 이용하기 편합니다.", author: "고객2", imageUrl: "path/to/image2.jpg" },
        { id: 3, productName: "포도", content: "배송이 빨라요!", author: "고객3", imageUrl: "path/to/image3.jpg" },
        // ... 더 많은 리뷰 데이터
    ];

    // 리뷰 정렬 (최신순)
    reviews.sort((a, b) => b.id - a.id);

    // 최근 리뷰 표시
    function displayRecentReviews() {
        recentReviews.innerHTML = '';
        reviews.slice(0, 2).forEach(review => {
            const reviewCard = document.createElement('div');
            reviewCard.className = 'review-card';
            reviewCard.innerHTML = `
                <div class="user-avatar"></div>
                <p class="user-name">${review.author} 고객님</p>
                <p class="review-text">${review.content}</p>
            `;
            recentReviews.appendChild(reviewCard);
        });
    }

    // 리뷰 표시 함수
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
                const accordionItem = document.createElement('div');
                accordionItem.className = 'accordion-item';

                let imageHTML = '';
                if (review.imageUrl) {
                    imageHTML = `<img src="${review.imageUrl}" alt="리뷰 이미지" class="review-image">`;
                }

                accordionItem.innerHTML = `
                    <div class="accordion-header">
                        <span>${review.productName} - ${review.author}</span>
                        <span class="accordion-arrow">▼</span>
                    </div>
                    <div class="accordion-content">
                        <div class="accordion-content-inner">
                            <p>${review.content}</p>
                            ${imageHTML}
                        </div>
                    </div>
                `;
                reviewsDisplay.appendChild(accordionItem);
            });
        }
        setupAccordion();
        updateNavigationButtons(reviewsToShow.length);
        updateReviewsDisplayOverflow();
    }

    function setupAccordion() {
        const accordionItems = document.querySelectorAll('.accordion-item');

        accordionItems.forEach(item => {
            const header = item.querySelector('.accordion-header');
            const content = item.querySelector('.accordion-content');
            const arrow = item.querySelector('.accordion-arrow');

            header.addEventListener('click', () => {
                // 다른 모든 아이템 닫기
                accordionItems.forEach(otherItem => {
                    if (otherItem !== item && otherItem.classList.contains('active')) {
                        otherItem.classList.remove('active');
                        otherItem.querySelector('.accordion-content').style.maxHeight = null;
                        otherItem.querySelector('.accordion-arrow').textContent = '▼';
                    }
                });

                // 현재 아이템 토글
                item.classList.toggle('active');
                if (item.classList.contains('active')) {
                    content.style.maxHeight = content.scrollHeight + "px";
                    arrow.textContent = '▲';
                } else {
                    content.style.maxHeight = null;
                    arrow.textContent = '▼';
                }

                // 리뷰 디스플레이의 overflow 상태 업데이트
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
        displayReviews(reviews, "고객 리뷰");
    }

    // 리뷰 검색
    function searchReviews() {
        const searchTerm = searchInput.value.toLowerCase();
        const searchCategory = searchType.value;
        const searchResults = reviews.filter(review =>
            review[searchCategory].toLowerCase().includes(searchTerm)
        );
        currentPage = 0;
        displayReviews(searchResults, "검색 결과");
    }

    // 모든 리뷰 표시
    function showAllReviews() {
        currentPage = 0;
        displayReviews(reviews, "모든 리뷰");
    }

    // 리뷰 작성 폼 제출 처리
    const reviewForm = document.querySelector('.review-form form');
    reviewForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const productName = document.getElementById('product-name').value;
        const reviewContent = document.getElementById('review-content').value;

        // 새 리뷰 추가
        addReview(productName, reviewContent);

        // 폼 초기화
        reviewForm.reset();
    });

    // 새 리뷰 추가 함수
    function addReview(product, content, imageUrl = '') {
        const isLoggedIn = checkLoginStatus();
        let author = isLoggedIn ? getCurrentUserName() : "비회원";

        const newReview = {
            id: reviews.length + 1,
            productName: product,
            content: content,
            author: author,
            imageUrl: imageUrl
        };

        reviews.unshift(newReview);
        currentPage = 0;
        displayReviews(reviews, "고객 리뷰");
        displayRecentReviews();
    }

    // 로그인 상태 확인 함수 (예시)
    function checkLoginStatus() {
        return false; // 실제 구현에서는 적절히 수정
    }

    // 현재 사용자 이름 가져오기 함수 (예시)
    function getCurrentUserName() {
        return "로그인사용자"; // 실제 구현에서는 적절히 수정
    }

    // 이벤트 리스너 설정
    searchButton.addEventListener('click', searchReviews);
    viewAllButton.addEventListener('click', showAllReviews);
    prevButton.addEventListener('click', () => navigateReviews('prev'));
    nextButton.addEventListener('click', () => navigateReviews('next'));

    // 초기 리뷰 표시
    displayReviews(reviews, "고객 리뷰");

    // 초기 최근 리뷰 표시
    displayRecentReviews();
});