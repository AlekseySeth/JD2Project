function setDelivery(deliveryId) {
    $.ajax('/cart', {
        method: 'POST',
        data: deliveryId
    })
}