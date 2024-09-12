let currentPage = 1;
let itemsPerPage = 6;
let currentSearchType = '';
let currentSearchKeyword = '';

function fetchNotices(page = currentPage, size = itemsPerPage) {
    let url = `/api/notice/list?page=${page}&size=${size}`;
    if (currentSearchType && currentSearchKeyword) {
        url += `&searchType=${currentSearchType}&searchKeyword=${encodeURIComponent(currentSearchKeyword)}`;
    }

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector(".notice-list tbody");
            tbody.innerHTML = "";
            const totalNotices = data.totalNotices;

            data.notices.forEach((notice, index) => {
                let newRow = document.createElement('tr');
                newRow.setAttribute('data-notice-id', notice.noticeNo);

                const displayNumber = totalNotices - (page - 1) * size - index;

                newRow.innerHTML = `
                    <td>
                        <input type="checkbox" class="notice-pin-checkbox" 
                               ${notice.noticePinned ? 'checked' : ''} 
                               onchange="toggleNoticePinned(${notice.noticeNo}, this.checked)">
                    </td>
                    <td>${displayNumber}</td>
                    <td>${notice.noticeTitle}</td>
                    <td>${notice.noticeContent}</td>
                    <td>${notice.noticeWriter}</td>
                    <td>${new Date(notice.noticeDate).toLocaleDateString()}</td>
                    <td>
                        <button class="btn-reply" onclick="toggleEditForm(${notice.noticeNo})">수정</button>
                        <button class="btn-delete" onclick="deleteNotice(${notice.noticeNo})">삭제</button>
                    </td>
                `;
                tbody.appendChild(newRow);
            });

            updatePagination(data.currentPage, data.totalPages);
        })
        .catch(error => {
            console.error('Error fetching notices:', error);
        });
}

function updatePagination(currentPage, totalPages) {
    const pagination = document.querySelector('.pagination');
    pagination.innerHTML = '';

    if (currentPage > 1) {
        const prevButton = document.createElement('span');
        prevButton.textContent = '<<';
        prevButton.classList.add('page-link');
        prevButton.addEventListener('click', () => fetchNotices(currentPage - 1, itemsPerPage));
        pagination.appendChild(prevButton);
    }

    for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('span');
        pageLink.textContent = i;
        pageLink.classList.add('page-number', 'page-link');
        if (i === currentPage) {
            pageLink.classList.add('active');
        }
        pageLink.addEventListener('click', () => fetchNotices(i, itemsPerPage));
        pagination.appendChild(pageLink);
    }

    if (currentPage < totalPages) {
        const nextButton = document.createElement('span');
        nextButton.textContent = '>>';
        nextButton.classList.add('page-link');
        nextButton.addEventListener('click', () => fetchNotices(currentPage + 1, itemsPerPage));
        pagination.appendChild(nextButton);
    }
}

function searchNotices() {
    currentSearchType = document.getElementById('search-type').value;
    currentSearchKeyword = document.getElementById('searchKeyword').value;
    currentPage = 1;
    fetchNotices();
}

document.addEventListener('DOMContentLoaded', () => {
    fetchNotices();

    document.getElementById('items-per-page').addEventListener('change', function() {
        itemsPerPage = parseInt(this.value);
        fetchNotices(1, itemsPerPage);
    });

    document.querySelector('.btn-search').addEventListener('click', searchNotices);

    document.getElementById('searchKeyword').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            searchNotices();
        }
    });

    document.getElementById('noticeForm').addEventListener('submit', function(e) {
        e.preventDefault();
        createNotice();
    });
});

function createNotice() {
    const title = document.getElementById('notice-title').value;
    const content = document.getElementById('notice-content').value;

    fetch('/api/notice/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            noticeTitle: title,
            noticeContent: content,
            noticeWriter: '관리자',
            userId: 'admin',
            noticePinned: 0
        }),
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            fetchNotices();
            document.getElementById('notice-title').value = '';
            document.getElementById('notice-content').value = '';
        })
        .catch((error) => {
            alert('공지사항 생성 중 오류가 발생했습니다: ' + error.message);
        });
}

function toggleEditForm(noticeNo) {
    const row = document.querySelector(`tr[data-notice-id="${noticeNo}"]`);
    if (!row) return;

    let editForm = row.nextElementSibling;
    if (editForm && editForm.classList.contains('edit-form')) {
        editForm.remove();
    } else {
        const title = row.querySelector('td:nth-child(3)').textContent;
        const content = row.querySelector('td:nth-child(4)').textContent;

        editForm = document.createElement('tr');
        editForm.className = 'edit-form';
        editForm.innerHTML = `
            <td colspan="7">
                <input type="text" id="editTitle_${noticeNo}" value="${title}">
                <textarea id="editContent_${noticeNo}">${content}</textarea>
                <button onclick="updateNotice(${noticeNo})">수정 완료</button>
            </td>
        `;
        row.insertAdjacentElement('afterend', editForm);
    }
}

function updateNotice(noticeNo) {
    const title = document.getElementById(`editTitle_${noticeNo}`).value;
    const content = document.getElementById(`editContent_${noticeNo}`).value;

    fetch(`/api/notice/update/${noticeNo}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            noticeTitle: title,
            noticeContent: content,
        }),
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            fetchNotices();
        })
        .catch((error) => {
            alert('공지사항 수정 중 오류가 발생했습니다.');
        });
}

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
            .catch((error) => {
                alert('공지사항 삭제 중 오류가 발생했습니다.');
            });
    }
}

function toggleNoticePinned(noticeNo, isPinned) {
    const pinnedNotices = document.querySelectorAll('.notice-pin-checkbox:checked').length;

    if (isPinned && pinnedNotices > 3) {
        alert('공지는 최대 3개까지만 체크됩니다.');
        event.target.checked = false;
        return;
    }

    fetch(`/api/notice/update/${noticeNo}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            noticePinned: isPinned ? 1 : 0
        }),
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            fetchNotices();
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('공지사항 고정 상태 변경 중 오류가 발생했습니다.');
        });
}