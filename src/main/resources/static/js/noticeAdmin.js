let currentPage = 1;
let itemsPerPage = 6;

// 공지사항 목록 가져오기
function fetchNotices(page = currentPage, size = itemsPerPage) {
    fetch(`/api/notice/list?page=${page}&size=${size}`)
        .then(response => response.json())
        .then(data => {
            console.log(data); // 데이터를 확인하는 콘솔 로그 추가
            const tbody = document.querySelector(".notice-list tbody");
            tbody.innerHTML = "";

            data.notices.forEach(notice => {
                let newRow = document.createElement('tr');
                newRow.innerHTML = `
                    <td><input type="checkbox" class="notice-checkbox"></td>
                    <td>${notice.noticeNo}</td>
                    <td>${notice.noticeTitle}</td>
                    <td>${notice.noticeContent}</td>
                    <td>${notice.noticeWriter}</td>
                    <td>${new Date(notice.noticeDate).toLocaleDateString()}</td>
                    <td>
                        <button class="btn-reply" onclick="toggleEditForm(this)">수정</button>
                        <button class="btn-delete" onclick="deleteNotice(${notice.noticeNo})">삭제</button>
                    </td>
                `;
                tbody.appendChild(newRow);

                let editRow = document.createElement('tr');
                editRow.className = "edit-row";
                editRow.style.display = "none";
                editRow.innerHTML = `
                    <td colspan="7">
                        <div class="edit-form">
                            <h3>공지사항 수정</h3>
                            <label>제목</label>
                            <input type="text" name="title" value="${notice.noticeTitle}">
                            <label>내용</label>
                            <textarea name="content">${notice.noticeContent}</textarea>
                            <div class="edit-form-buttons">
                                <button class="btn-submit-reply" onclick="updateNotice(${notice.noticeNo}, this)">수정 완료</button>
                                <button class="btn-close" onclick="toggleEditForm(this)">닫기</button>
                            </div>
                        </div>
                    </td>
                `;
                tbody.appendChild(editRow);
            });

            updatePagination(data.currentPage, data.totalPages);
        })
        .catch(error => {
            console.error('Error fetching notices:', error);
        });
}

// 페이징 처리 함수
function updatePagination(currentPage, totalPages) {
    const pagination = document.querySelector('.pagination');
    pagination.innerHTML = '';

    if (currentPage > 1) {
        const prevButton = document.createElement('span');
        prevButton.textContent = '<<';
        prevButton.addEventListener('click', () => fetchNotices(currentPage - 1, itemsPerPage));
        pagination.appendChild(prevButton);
    }

    for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('span');
        pageLink.textContent = i;
        pageLink.classList.add('page-number');
        if (i === currentPage) {
            pageLink.classList.add('active');
        }
        pageLink.addEventListener('click', () => fetchNotices(i, itemsPerPage));
        pagination.appendChild(pageLink);
    }

    if (currentPage < totalPages) {
        const nextButton = document.createElement('span');
        nextButton.textContent = '>>';
        nextButton.addEventListener('click', () => fetchNotices(currentPage + 1, itemsPerPage));
        pagination.appendChild(nextButton);
    }
}

// 등록 함수
function createNotice() {
    const noticeTitle = document.getElementById("notice-title").value;
    const noticeContent = document.getElementById("notice-content").value;

    if (noticeTitle && noticeContent) {
        fetch('/api/notice/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                noticeTitle: noticeTitle,
                noticeContent: noticeContent,
                noticeWriter: "관리자",
                noticeDate: new Date().toISOString()
            })
        })
            .then(response => response.ok ? response.text() : Promise.reject(response.statusText))
            .then(data => {
                alert(data);
                fetchNotices();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('공지사항 등록 중 오류가 발생했습니다: ' + error);
            });

        document.getElementById('notice-title').value = '';
        document.getElementById('notice-content').value = '';
    } else {
        alert('제목과 내용을 입력하세요.');
    }
}

// 수정 함수
function updateNotice(noticeNo, button) {
    const titleInput = button.closest('tr').querySelector('input[name="title"]').value;
    const contentTextarea = button.closest('tr').querySelector('textarea[name="content"]').value;

    fetch(`/api/notice/update/${noticeNo}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            noticeTitle: titleInput,
            noticeContent: contentTextarea
        })
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            fetchNotices();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('공지사항 수정 중 오류가 발생했습니다: ' + error);
        });
}

// 삭제 함수
function deleteNotice(noticeNo) {
    if (confirm('정말로 이 공지사항을 삭제하시겠습니까?')) {
        fetch(`/api/notice/delete/${noticeNo}`, {
            method: 'DELETE',
        })
            .then(response => response.text())
            .then(data => {
                alert(data);
                fetchNotices();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('공지사항 삭제 중 오류가 발생했습니다: ' + error);
            });
    }
}

// 초기 로드
document.addEventListener('DOMContentLoaded', () => {
    fetchNotices();
});

// 검색 기능
document.querySelector('.btn-search').addEventListener('click', function() {
    const searchField = document.querySelector('.search-bar select').value;
    const searchTerm = document.querySelector('.search-bar input').value.toLowerCase();
    const rows = document.querySelectorAll('.notice-list tbody tr:not(.edit-row)');

    rows.forEach(row => {
        const title = row.children[2].textContent.toLowerCase();
        const content = row.children[3].textContent.toLowerCase();
        const author = row.children[4].textContent.toLowerCase();

        let match = false;
        if (searchField === '제목' && title.includes(searchTerm)) {
            match = true;
        } else if (searchField === '내용' && content.includes(searchTerm)) {
            match = true;
        } else if (searchField === '작성자' && author.includes(searchTerm)) {
            match = true;
        }

        if (match) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});
