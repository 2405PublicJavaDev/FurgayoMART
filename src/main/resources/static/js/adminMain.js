document.addEventListener('DOMContentLoaded', function() {
    const contentGrids = document.querySelectorAll('.content-grid');
    const adminCards = document.querySelectorAll('.admin-card');

    function toggleEditMode(container) {
        const textElement = container.querySelector('p');
        const editButton = container.querySelector('.edit-button');
        const textarea = container.querySelector('.edit-text');
        const updateButton = container.querySelector('.update-button');

        // 수정 모드 토글
        if (textarea.style.display === 'none' || textarea.style.display === '') {
            textElement.style.display = 'none'; // 기존 텍스트 숨기기
            editButton.style.display = 'none'; // 수정 버튼 숨기기
            textarea.style.display = 'block';  // 텍스트 입력창 보이기
            updateButton.style.display = 'inline-block'; // 업데이트 버튼 보이기
            textarea.value = textElement.textContent; // 텍스트 필드에 기존 텍스트 설정
        } else {
            textElement.style.display = 'block'; // 기존 텍스트 보이기
            editButton.style.display = 'inline-block'; // 수정 버튼 보이기
            textarea.style.display = 'none'; // 텍스트 입력창 숨기기
            updateButton.style.display = 'none'; // 업데이트 버튼 숨기기
        }
    }

    function updateText(container) {
        const textElement = container.querySelector('p');
        const textarea = container.querySelector('.edit-text');
        const newText = textarea.value.trim();
        if (newText) {
            const formattedText = newText.replace(/\n/g, '<br>');
            textElement.innerHTML = formattedText; // 텍스트 업데이트
        }
        toggleEditMode(container); // 수정 모드 종료
    }

    // 각 그리드에 수정 및 업데이트 이벤트 추가
    contentGrids.forEach(grid => {
        const editButton = grid.querySelector('.edit-button');
        const updateButton = grid.querySelector('.update-button');

        editButton.addEventListener('click', () => toggleEditMode(grid));
        updateButton.addEventListener('click', () => updateText(grid));
    });

    // 관리자 카드에 수정 및 업데이트 이벤트 추가
    adminCards.forEach(card => {
        const editButton = card.querySelector('.edit-button');
        const updateButton = card.querySelector('.update-button');

        editButton.addEventListener('click', () => toggleEditMode(card));
        updateButton.addEventListener('click', () => updateText(card));
    });
});
