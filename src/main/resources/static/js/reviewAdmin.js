document.addEventListener('DOMContentLoaded', function() {
    const selectAllCheckbox = document.getElementById('select-all-checkbox');
    const reviewCheckboxes = document.querySelectorAll('.review-checkbox');

    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('change', function() {
            const isChecked = this.checked;
            reviewCheckboxes.forEach(checkbox => {
                checkbox.checked = isChecked;
            });
        });
    }

    reviewCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            const allChecked = Array.from(reviewCheckboxes).every(cb => cb.checked);
            if (selectAllCheckbox) {
                selectAllCheckbox.checked = allChecked;
            }
        });
    });
});

function toggleReplyPanel(button) {
    const row = button.closest('tr');
    const replyPanel = row.nextElementSibling;

    if (replyPanel.style.display === 'none') {
        replyPanel.style.display = 'table-row';
        button.textContent = '닫기';
    } else {
        replyPanel.style.display = 'none';
        button.textContent = '답글';
    }
}

// 답글 등록 버튼 클릭 이벤트 처리
document.addEventListener('click', function(e) {
    if (e.target && e.target.classList.contains('btn-submit-reply')) {
        const replyContent = e.target.previousElementSibling.value;
        if (replyContent.trim() !== '') {
            alert('답글이 등록되었습니다: ' + replyContent);
            // 여기에 서버로 답글을 전송하는 코드를 추가할 수 있습니다.
        } else {
            alert('답글 내용을 입력해주세요.');
        }
    }
});

// 삭제 버튼 클릭 이벤트 처리 (예시)
document.addEventListener('click', function(e) {
    if (e.target && e.target.classList.contains('btn-delete')) {
        if (confirm('이 리뷰를 삭제하시겠습니까?')) {
            // 여기에 서버로 삭제 요청을 보내는 코드를 추가할 수 있습니다.
            alert('리뷰가 삭제되었습니다.');
            e.target.closest('tr').remove();
        }
    }
});

// 검색 기능 (예시)
document.querySelector('.btn-search').addEventListener('click', function() {
    const searchType = document.querySelector('.search-bar select').value;
    const searchTerm = document.querySelector('.search-bar input').value;

    // 여기에 실제 검색 로직을 구현할 수 있습니다.
    alert(`검색 유형: ${searchType}, 검색어: ${searchTerm}`);
});

// 페이지 로드 시 콘솔에 메시지 출력 (디버깅용)
console.log('JavaScript 파일이 로드되었습니다.');