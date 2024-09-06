// 수정 폼을 표시하거나 숨기는 함수
function toggleEditForm(button) {
    const allEditRows = document.querySelectorAll('.edit-row');
    const allButtons = document.querySelectorAll('.btn-reply');

    // 다른 공지사항 수정 창 닫기
    allEditRows.forEach(editRow => {
        editRow.style.display = 'none';
    });

    allButtons.forEach(btn => {
        btn.textContent = '수정';
    });

    const row = button.closest('tr');
    const editRow = row.nextElementSibling;

    if (editRow && (editRow.style.display === 'none' || editRow.style.display === '')) {
        editRow.style.display = 'table-row';
        button.textContent = '닫기';
    }
}

// Rest API를 통해 서버로부터 공지사항 목록 불러오기
document.addEventListener("DOMContentLoaded", function () {
    fetchNotices();

    // 공지사항 등록 버튼 클릭 이벤트 (Rest API를 통한 등록)
    document.querySelector("form").addEventListener("submit", function (event) {
        event.preventDefault(); // 폼이 제출되지 않도록 기본 동작을 막음
        createNotice(); // 공지사항 등록 함수 호출
    });
});

// 공지사항 목록 불러오기 (Rest API)
function fetchNotices() {
    fetch('/api/notice/list')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            let tbody = document.querySelector(".notice-list tbody");
            tbody.innerHTML = ""; // 기존 데이터를 초기화

            data.forEach(notice => {
                let newRow = document.createElement('tr');
                let editRow = document.createElement('tr');

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

                editRow.className = "edit-row";
                editRow.style.display = "none";
                editRow.innerHTML = `
                    <td colspan="7">
                        <div class="edit-form">
                            <h3>공지사항 수정</h3>
                            <label>제목</label>
                            <input type="text" value="${notice.noticeTitle}">
                            <label>내용</label>
                            <textarea>${notice.noticeContent}</textarea>
                            <div class="edit-form-buttons">
                                <button class="btn-submit-reply" onclick="updateNotice(${notice.noticeNo}, this)">수정 완료</button>
                                <button class="btn-close" onclick="toggleEditForm(this)">닫기</button>
                            </div>
                        </div>
                    </td>
                `;

                tbody.appendChild(newRow);
                tbody.appendChild(editRow);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('공지사항 목록을 불러오는 중 오류가 발생했습니다.');
        });
}

// 공지사항 등록 (Rest API)
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
                noticeWriter: "관리자", // 하드코딩된 작성자
                noticeDate: new Date().toISOString() // 현재 날짜를 ISO 형식으로 추가
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                alert(data); // 서버에서 온 메시지 출력
                fetchNotices(); // 등록 후 리스트 갱신
            })
            .catch(error => {
                console.error('Error:', error);
                alert('공지사항 등록 중 오류가 발생했습니다.');
            });

        // 입력 필드 초기화
        document.getElementById('notice-title').value = '';
        document.getElementById('notice-content').value = '';
    } else {
        alert('제목과 내용을 입력하세요.');
    }
}

// 공지사항 수정 (Rest API)
function updateNotice(noticeNo, button) {
    const titleInput = button.closest('tr').querySelector('input').value;
    const contentTextarea = button.closest('tr').querySelector('textarea').value;

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
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            alert(data); // 서버에서 온 메시지 출력
            fetchNotices(); // 수정 후 리스트 갱신
        })
        .catch(error => {
            console.error('Error:', error);
            alert('공지사항 수정 중 오류가 발생했습니다.');
        });
}

// 공지사항 삭제 (Rest API)
function deleteNotice(noticeNo) {
    if (confirm('정말로 이 공지사항을 삭제하시겠습니까?')) {
        fetch(`/api/notice/delete/${noticeNo}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                alert(data); // 서버에서 온 메시지 출력
                fetchNotices(); // 삭제 후 리스트 갱신
            })
            .catch(error => {
                console.error('Error:', error);
                alert('공지사항 삭제 중 오류가 발생했습니다.');
            });
    }
}

// 전체 선택 체크박스와 개별 체크박스 동기화
document.addEventListener('DOMContentLoaded', function() {
    const selectAllCheckbox = document.getElementById('select-all-checkbox');
    const noticeCheckboxes = document.querySelectorAll('.notice-checkbox');

    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('change', function() {
            const isChecked = this.checked;
            noticeCheckboxes.forEach(checkbox => {
                checkbox.checked = isChecked;
            });
        });
    }

    // 검색 기능 구현
    const searchButton = document.querySelector('.btn-search');
    searchButton.addEventListener('click', function() {
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

    // 보기 개수 설정 (6개, 10개, 20개씩 보기)
    const itemsPerPageSelect = document.getElementById('items-per-page');
    let currentPage = 1;
    const rows = document.querySelectorAll('.notice-list tbody tr:not(.edit-row)');

    function showPage(page, itemsPerPage) {
        const start = (page - 1) * itemsPerPage;
        const end = start + itemsPerPage;

        rows.forEach((row, index) => {
            if (index >= start && index < end) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    }

    itemsPerPageSelect.addEventListener('change', function() {
        const itemsPerPage = parseInt(this.value, 10);
        currentPage = 1;
        showPage(currentPage, itemsPerPage);
    });

    // 페이지 이동
    const pageButtons = document.querySelectorAll('.page-number');
    pageButtons.forEach(button => {
        button.addEventListener('click', function() {
            currentPage = parseInt(this.dataset.page, 10);
            const itemsPerPage = parseInt(itemsPerPageSelect.value, 10);
            showPage(currentPage, itemsPerPage);
        });
    });

    // 초기 6개만 표시
    showPage(currentPage, 6);
});