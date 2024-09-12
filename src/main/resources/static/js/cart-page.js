document.addEventListener('DOMContentLoaded', function() {
	const cartItems = document.querySelectorAll('.cart-item');
	const totalPriceElement = document.getElementById('total-price');
	const finalPriceElement = document.getElementById('final-price');

	function updateTotal() {
		let total = 0;
		cartItems.forEach(item => {
			const checkbox = item.querySelector('.item-check');
			if (checkbox.checked) {
				const price = parseInt(item.querySelector('.item-price').textContent.replace(/[^0-9]/g, ''));
				const quantity = parseInt(item.querySelector('.quantity-input').value);
				total += price * quantity;
			}
		});
		totalPriceElement.textContent = total.toLocaleString();
		finalPriceElement.textContent = total.toLocaleString();
	}

	cartItems.forEach(item => {
		const minusBtn = item.querySelector('.minus');
		const plusBtn = item.querySelector('.plus');
		const quantityInput = item.querySelector('.quantity-input');
		const deleteBtn = item.querySelector('.delete-btn');
		const checkbox = item.querySelector('.item-check');

		minusBtn.addEventListener('click', () => {
			if (parseInt(quantityInput.value) > 1) {
				quantityInput.value = parseInt(quantityInput.value) - 1;
				updateTotal();
			}
		});

		plusBtn.addEventListener('click', () => {
			quantityInput.value = parseInt(quantityInput.value) + 1;
			updateTotal();
		});

		deleteBtn.addEventListener('click', () => {
			item.remove();
			updateTotal();
		});

		checkbox.addEventListener('change', updateTotal);
		quantityInput.addEventListener('change', updateTotal);
	});

	updateTotal(); // 초기 총액 계산
});