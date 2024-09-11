document.addEventListener('DOMContentLoaded', function () {
    const searchButton = document.querySelector('.btn-search');
    const searchTypeSelect = document.querySelector('#search-type');
    const searchKeywordInput = document.querySelector('#search-keyword');
    const reviewListContainer = document.querySelector('#review-list-container');
    const viewAllButton = document.getElementById('view-all-button');
    const prevButton = document.getElementById('prev-review');
    const nextButton = document.getElementById('next-review');

    let currentPage = 0;
    let reviews = [];

    function fetchUserReviews(searchType = null, searchKeyword = null, page = 0) {
        let url = `/api/reviews?page=${page}&size=9`; // 9개의 리뷰를 가져옵니다 (3페이지 분량)
        if (searchType && searchKeyword) {
            url += `&searchType=${searchType}&searchKeyword=${searchKeyword}`;
        }

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Received reviews:', data);
                reviews = data.content;
                currentPage = 0;
                displayReviews();
            })
            .catch(error => {
                console.error('Error:', error);
                if (reviewListContainer) {
                    reviewListContainer.innerHTML = '<p>리뷰를 불러오는 중 오류가 발생했습니다.</p>';
                }
            });
    }

    function displayReviews() {
        if (!reviewListContainer) {
            console.error('Review list container not found');
            return;
        }

        reviewListContainer.innerHTML = '';

        if (!reviews || reviews.length === 0) {
            reviewListContainer.innerHTML = '<p>표시할 리뷰가 없습니다.</p>';
            return;
        }

        const startIndex = currentPage * 3;
        const endIndex = startIndex + 3;
        const currentReviews = reviews.slice(startIndex, endIndex);

        currentReviews.forEach(review => {
            const reviewItem = document.createElement('div');
            reviewItem.className = 'review-item';
            reviewItem.innerHTML = `
                <div class="accordion-header">
                    <span>${review.pname || '상품명 없음'} - ${review.writerId || '작성자 없음'}</span>
                    <span class="accordion-arrow">▼</span>
                </div>
                <div class="accordion-content">
                    <div class="accordion-content-inner">
                        <h3>${review.reTitle || '제목 없음'}</h3>
                        <p>${review.reContent || '내용 없음'}</p>
                    </div>
                </div>
            `;
            if (review.fileName) {
                const img = document.createElement('img');
                img.src = `/uploads/${review.fileName}`;
                img.alt = "Review Image";
                img.className = "review-image";
                img.onclick = function() {
                    showOriginalImage(`/uploads/${review.fileName}`);
                };
                reviewItem.querySelector('.accordion-content-inner').appendChild(img);
            }
            reviewListContainer.appendChild(reviewItem);
        });

        setupAccordion();
        updateNavigationButtons();
    }

    function setupAccordion() {
        const accordionItems = document.querySelectorAll('.review-item');

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
            });
        });
    }

    function updateNavigationButtons() {
        prevButton.disabled = currentPage === 0;
        nextButton.disabled = (currentPage + 1) * 3 >= reviews.length;
    }

    if (searchButton) {
        searchButton.addEventListener('click', function () {
            const searchType = searchTypeSelect ? searchTypeSelect.value : null;
            const searchKeyword = searchKeywordInput ? searchKeywordInput.value.trim() : null;

            if (!searchKeyword) {
                alert('검색어를 입력해주세요.');
                return;
            }

            fetchUserReviews(searchType, searchKeyword);
        });
    }

    if (viewAllButton) {
        viewAllButton.addEventListener('click', function () {
            fetchUserReviews();
        });
    }

    prevButton.addEventListener('click', function() {
        if (currentPage > 0) {
            currentPage--;
            displayReviews();
        }
    });

    nextButton.addEventListener('click', function() {
        if ((currentPage + 1) * 3 < reviews.length) {
            currentPage++;
            displayReviews();
        }
    });

    window.showOriginalImage = function(imageSrc) {
        const popup = document.createElement('div');
        popup.className = 'image-popup';
        popup.innerHTML = `
            <div class="image-popup-content">
                <img src="${imageSrc}" alt="원본 이미지">
                <button onclick="this.parentElement.parentElement.remove()">닫기</button>
            </div>
        `;
        document.body.appendChild(popup);
    };

    // 리뷰 작성 폼 제출 처리
    const reviewForm = document.querySelector('.review-form form');
    if (reviewForm) {
        reviewForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const formData = new FormData(this);

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
                    this.reset();
                    fetchUserReviews(); // 리뷰 목록 새로고침
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('리뷰 작성 중 오류가 발생했습니다.');
                });
        });
    }

    // 초기 로드 시 모든 리뷰 불러오기
    fetchUserReviews();
});