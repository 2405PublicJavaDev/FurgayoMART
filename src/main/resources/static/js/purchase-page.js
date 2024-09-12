document.addEventListener('DOMContentLoaded', function() {
    const quantityInput = document.getElementById('quantity');
    const totalAmountSpan = document.getElementById('totalAmount');
	const finalAmountSpan = document.getElementById('finalAmount');
    const totalAmountInput = document.getElementById('totalAmountInput');
    const productPrice = parseFloat(document.querySelector('.product-details p').textContent.replace(/[^0-9.-]+/g,""));

    function updateTotalAmount() {
        const quantity = parseInt(quantityInput.value);
        const totalAmount = productPrice * quantity;
        totalAmountSpan.textContent = totalAmount.toLocaleString();
		finalAmountSpan.textContent = totalAmount.toLocaleString();
        totalAmountInput.value = totalAmount;
    }

    quantityInput.addEventListener('change', updateTotalAmount);
    updateTotalAmount();

    document.getElementById('purchaseButton').addEventListener('click', function(event) {
        event.preventDefault();
        if (confirm('무통장입금으로 결제하시겠습니까?')) {
            document.getElementById('purchaseForm').submit();
        }
    });
});