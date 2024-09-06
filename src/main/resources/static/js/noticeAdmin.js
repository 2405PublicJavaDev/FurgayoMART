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

// 공지사항 수정 반영 기능
document.addEventListener('click', function(e) {
    if (e.target && e.target.classList.contains('btn-submit-reply')) {
        const row = e.target.closest('tr').previousElementSibling;
        const titleInput = e.target.closest('tr').querySelector('input');
        const contentTextarea = e.target.closest('tr').querySelector('textarea');

        // 수정된 내용 반영
        row.children[2].textContent = titleInput.value;
        row.children[3].textContent = contentTextarea.value;

        alert('공지사항이 수정되었습니다.');
    }
});

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

    // 삭제 기능
    document.addEventListener('click', function(e) {
        if (e.target && e.target.classList.contains('btn-delete')) {
            const row = e.target.closest('tr');
            if (confirm('정말로 이 공지사항을 삭제하시겠습니까?')) {
                const editRow = row.nextElementSibling;
                if (editRow && editRow.classList.contains('edit-row')) {
                    editRow.remove(); // 수정 폼도 함께 삭제
                }
                row.remove(); // 선택된 공지사항 삭제
            }
        }
    });

    // 공지사항 등록 기능
    const form = document.querySelector('.notice-form form');
    const noticeList = document.querySelector('.notice-list tbody');

    form.addEventListener('submit', function(e) {
        e.preventDefault(); // 폼이 제출되지 않도록 기본 동작을 막음

        const title = document.getElementById('notice-title').value;
        const content = document.getElementById('notice-content').value;

        if (title && content) {
            // 새로운 공지사항 항목을 테이블에 추가
            const newRow = document.createElement('tr');
            const editRow = document.createElement('tr');

            newRow.innerHTML = `
                <td><input type="checkbox" class="notice-checkbox"></td>
                <td>1</td> <!-- 번호는 동적으로 할당 가능 -->
                <td>${title}</td>
                <td>${content}</td>
                <td>관리자</td>
                <td>${new Date().toISOString().slice(0, 10)}</td>
                <td><button class="btn-reply" onclick="toggleEditForm(this)">수정</button><button class="btn-delete">삭제</button></td>
            `;

            editRow.className = "edit-row";
            editRow.style.display = "none";
            editRow.innerHTML = `
                <td colspan="7">
                    <div class="edit-form">
                        <h3>공지사항 수정</h3>
                        <label>제목</label>
                        <input type="text" value="${title}">
                        <label>내용</label>
                        <textarea>${content}</textarea>
                        <div class="edit-form-buttons">
                            <button class="btn-submit-reply">수정 완료</button>
                            <button class="btn-close" onclick="toggleEditForm(this)">닫기</button>
                        </div>
                    </div>
                </td>
            `;

            noticeList.appendChild(newRow);
            noticeList.appendChild(editRow);

            // 입력 필드 초기화
            document.getElementById('notice-title').value = '';
            document.getElementById('notice-content').value = '';
        } else {
            alert('제목과 내용을 입력하세요.');
        }
    });

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
