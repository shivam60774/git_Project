const searchForm = document.getElementById('searchForm');
const updateForm = document.getElementById('updateForm');

// Fetch shipment from backend by tracking number
async function fetchShipmentByTrackingNumber(trackingNumber) {
    try {
        const response = await fetch(`/demo_desk/searchShipmentById?trackingNumber=${trackingNumber}`);
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        const data = await response.json(); // Parse JSON into JS object
        return Object.keys(data).length > 0 ? data : null;
    } catch (error) {
        console.error("Error fetching shipment:", error);
        return null;
    }
}

// Populate form fields with shipment data
function populateForm(data) {
    if (!data) {
        alert('No shipment found for this tracking number.');
        updateForm.style.display = 'none';
        return;
    }

    updateForm.style.display = 'block';
    updateForm.date.value = data.date || '';
    updateForm.cNote.value = data.cNote || '';
    updateForm.content.value = data.content || '';
    updateForm.consignorName.value = data.consignorName || '';
    updateForm.consignorMob.value = data.consignorMob || '';
    updateForm.consigneeName.value = data.consigneeName || '';
    updateForm.consigneeMob.value = data.consigneeMob || '';
    updateForm.consigneeAddress.value = data.consigneeAddress || '';
    updateForm.destination.value = data.destination || '';
    updateForm.pincode.value = data.pincode || '';
    updateForm.pieces.value = data.pieces || '';
    updateForm.declaredValue.value = data.declaredValue || '';
    updateForm.riskSurcharge.value = data.riskSurcharge || '';
    updateForm.actualWeight.value = data.actualWeight || '';
    updateForm.dimensions.value = data.dimensions || '';
}

// Handle search form submit
searchForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const trackingNumber = searchForm.trackingNumber.value.trim();
    if (!trackingNumber) return;
    updateForm.style.display = 'none';

    const data = await fetchShipmentByTrackingNumber(trackingNumber);
    populateForm(data);
});
