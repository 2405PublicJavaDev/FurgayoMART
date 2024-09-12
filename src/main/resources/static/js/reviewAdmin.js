document.addEventListener('DOMContentLoaded', function () {
    const selectAllCheckbox = document.getElementById('select-all-checkbox');
    const reviewList = document.getElementById('review-list');
    const deleteSelectedBtn = document.getElementById('delete-selected-btn');
    let currentlyOpenReplyPanel = null;

    fetchReviews();

    function formatDate(dateString) {
        const date = new Date(dateString);
        return date.toISOString().split('T')[0];
    }

    function formatDateTime(dateString) {
        const date = new Date(dateString);
        const datePart = date.toISOString().split('T')[0];
        const timePart = date.toTimeString().split(' ')[0];
        return `${datePart} ${timePart}`;
    }

    function addReviewToTable(review) {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><input type="checkbox" class="review-checkbox" data-review-id="${review.reviewId}"></td>
            <td>${review.reTitle}</td>
            <td>${review.reContent.substring(0, 20)}...</td>
            <td>${review.writerId}</td>
            <td>${formatDate(review.reviewDate)}</td>
            <td>${review.pname}</td>
            <td>
                <button class="btn-reply" onclick="toggleReplyPanel(this, ${review.reviewId})">답글</button>
                <button class="btn-delete" onclick="deleteReview(${review.reviewId})">삭제</button>
            </td>
        `;
        reviewList.appendChild(row);

        const replyPanelRow = document.createElement('tr');
        replyPanelRow.classList.add('reply-panel');
        replyPanelRow.style.display = 'none';
        replyPanelRow.innerHTML = `
            <td colspan="7">
                <div class="reply-content">
                    <h4>리뷰 답글 등록 화면</h4>
                    <p><strong>${review.reTitle}</strong></p>
                    <p>${review.writerId} | ${formatDateTime(review.reviewDate)}</p>
                    <p>${review.reContent}</p>
                    <textarea placeholder="답글을 입력하세요..."></textarea>
                    <button class="btn-submit-reply" onclick="submitReply(${review.reviewId})">답글 등록</button>
                </div>
            </td>
        `;
        reviewList.appendChild(replyPanelRow);
    }

    window.toggleReplyPanel = function (button, reviewId) {
        const row = button.closest('tr');
        const replyPanel = row.nextElementSibling;

        if (currentlyOpenReplyPanel && currentlyOpenReplyPanel !== replyPanel) {
            currentlyOpenReplyPanel.style.display = 'none';
            currentlyOpenReplyPanel.previousElementSibling.querySelector('.btn-reply').textContent = '답글';
        }

        if (replyPanel.style.display === 'none') {
            replyPanel.style.display = 'table-row';
            button.textContent = '닫기';
            currentlyOpenReplyPanel = replyPanel;
        } else {
            replyPanel.style.display = 'none';
            button.textContent = '답글';
            currentlyOpenReplyPanel = null;
        }
    };

    window.submitReply = function (reviewId) {
        const replyContent = currentlyOpenReplyPanel.querySelector('textarea').value;

        if (replyContent.trim() === '') {
            alert('답글 내용을 입력해주세요.');
            return;
        }

        fetch(`/api/reviews/${reviewId}/reply`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(replyContent),
        })
            .then(response => {
                if (response.ok) {
                    alert('답글이 성공적으로 등록되었습니다.');

                    if (currentlyOpenReplyPanel) {
                        currentlyOpenReplyPanel.style.display = 'none';
                        currentlyOpenReplyPanel.previousElementSibling.querySelector('.btn-reply').textContent = '답글';
                        currentlyOpenReplyPanel = null;
                    }
                }
            })
            .catch(error => console.error('Error:', error));
    };

    window.deleteReview = function (reviewId) {
        if (!confirm('정말 이 리뷰를 삭제하시겠습니까?')) return;

        fetch(`/api/reviews/${reviewId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('리뷰가 삭제되었습니다.');
                    location.reload();
                }
            })
            .catch(error => console.error('Error:', error));
    };

    deleteSelectedBtn.addEventListener('click', function () {
        const selectedCheckboxes = document.querySelectorAll('.review-checkbox:checked');
        const selectedReviewIds = Array.from(selectedCheckboxes).map(checkbox => checkbox.getAttribute('data-review-id'));

        if (selectedReviewIds.length === 0) {
            alert('삭제할 항목을 선택해주세요.');
            return;
        }

        if (!confirm('정말 선택한 리뷰들을 삭제하시겠습니까?')) return;

        fetch(`/api/reviews/bulk-delete`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ reviewIds: selectedReviewIds }),
        })
            .then(response => {
                if (response.ok) {
                    alert('선택한 리뷰들이 삭제되었습니다.');
                    location.reload();
                }
            })
            .catch(error => console.error('Error:', error));
    });

    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('change', function () {
            const isChecked = this.checked;
            document.querySelectorAll('.review-checkbox').forEach(checkbox => {
                checkbox.checked = isChecked;
            });
        });
    }

    const searchButton = document.querySelector('.btn-search');
    const searchTypeSelect = document.querySelector('#search-type');
    const searchKeywordInput = document.querySelector('#search-keyword');

    searchButton.addEventListener('click', function () {
        const searchType = searchTypeSelect.value;
        const searchKeyword = searchKeywordInput.value.trim();

        if (!searchKeyword) {
            alert('검색어를 입력해주세요.');
            return;
        }

        fetchReviews(searchType, searchKeyword);
    });

    function fetchReviews(searchType = null, searchKeyword = null, page = 0) {
        let url = `/api/reviews?page=${page}`;

        if (searchType && searchKeyword) {
            url += `&searchType=${searchType}&searchKeyword=${searchKeyword}`;
        }

        fetch(url)
            .then(response => response.json())
            .then(data => {
                reviewList.innerHTML = '';

                if (data.content && data.content.length > 0) {
                    data.content.forEach(review => {
                        addReviewToTable(review);
                    });
                }

                const pagination = document.querySelector('.pagination');
                pagination.innerHTML = '';
                for (let i = 0; i < data.totalPages; i++) {
                    const pageLink = document.createElement('a');
                    pageLink.href = '#';
                    pageLink.className = 'page-link';
                    pageLink.textContent = i + 1;
                    if (i === data.number) {
                        pageLink.classList.add('active');
                    }
                    pageLink.addEventListener('click', function (e) {
                        e.preventDefault();
                        fetchReviews(searchType, searchKeyword, i);
                    });
                    pagination.appendChild(pageLink);
                }
            })
            .catch(error => console.error('Error:', error));
    }
});