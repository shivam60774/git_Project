const searchForm = document.getElementById('searchForm');
const customerNameSelect = document.getElementById('customerName');
const pdfContainer = document.getElementById('pdfContainer');
const pdfPreview = document.getElementById('pdfPreview');
const downloadPdfBtn = document.getElementById('downloadPdfBtn');

let currentPdfBlobUrl = null;

// Fetch customer names from backend
async function fetchCustomerNames() {
    try {
        const response = await fetch('/demo_desk/getCustomers'); 
        if (!response.ok) throw new Error('Failed to fetch customer names');
        const customers = await response.json();
        return customers;
    } catch (error) {
        console.error(error);
        return [];
    }
}

// Populate dropdown with customer names
function populateCustomerNames(customers) {
    customerNameSelect.innerHTML = '<option value="" disabled selected>Select a customer</option>';

    customers.forEach(customer => {
        const option = document.createElement('option');
        option.value = customer.name || customer;
        option.textContent = customer.name || customer;
        customerNameSelect.appendChild(option);
    });
}

// Generate PDF from shipment data
async function generatePdf(shipments, customerName) {
	const { jsPDF } = window.jspdf;
	const doc = new jsPDF();

	doc.setFontSize(18);
	doc.text(`Shipments for Customer: ${customerName}`, 14, 22);

	const columns = [
		{ header: 'Sr.No.', dataKey: 'serial' },
		{ header: 'Date', dataKey: 'date' },
		{ header: 'Tracking No', dataKey: 'cNote' },
		{ header: 'From', dataKey: 'consignorName' },
		{ header: 'To', dataKey: 'consigneeName' },
		{ header: 'Location', dataKey: 'destination' },
		{ header: 'Pincode', dataKey: 'pincode' },
		{ header: 'Type', dataKey: 'shipmentType' },
		{ header: 'Weight', dataKey: 'actualWeight' }
	];

	// Sort shipments by tracking number (if needed)
	 shipments.sort((a, b) => new Date(a.date) - new Date(b.date));

	// Generate rows with auto serial number
	const rows = shipments.map((s, index) => {
		return columns.map(col => {
			if (col.dataKey === 'serial') return index + 1; // Auto-generate Sr.No.
			return s[col.dataKey] || '';
		});
	});

	doc.autoTable({
		startY: 30,
		head: [columns.map(col => col.header)],
		body: rows,
		styles: { fontSize: 10 },
		headStyles: { fillColor: [41, 128, 185] },
		theme: 'striped',
		margin: { left: 10, right: 10 }
	});

	const pdfBlob = doc.output('blob');

	if (currentPdfBlobUrl) {
		URL.revokeObjectURL(currentPdfBlobUrl);
	}

	currentPdfBlobUrl = URL.createObjectURL(pdfBlob);
	pdfPreview.src = currentPdfBlobUrl;
	pdfContainer.style.display = 'block';
}


downloadExcelBtn.addEventListener("click", () => {
	if (!shipmentData || shipmentData.length === 0) return;

	// Convert JSON → Worksheet
	const worksheet = XLSX.utils.json_to_sheet(shipmentData);
	const workbook = XLSX.utils.book_new();
	XLSX.utils.book_append_sheet(workbook, worksheet, "Shipments");

	// Generate and download .xlsx file
	XLSX.writeFile(workbook, "shipments.xlsx");
});

// On page load, fetch and populate customer names
window.addEventListener('DOMContentLoaded', async () => {
    const customers = await fetchCustomerNames();
    populateCustomerNames(customers);
});

// Fetch shipment data by customer name
async function fetchShipmentByCustomerName(customerName) {
    try {
        const response = await fetch(`/demo_desk/searchByName?name=${encodeURIComponent(customerName)}`);
        if (!response.ok) throw new Error('Failed to fetch shipment data');
        const shipments = await response.json();
        return shipments;
    } catch (error) {
        console.error(error);
        alert('Error fetching shipment data');
        return [];
    }
}

// Handle form submission
searchForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const customerName = customerNameSelect.value;
    if (!customerName) return;

    const shipments = await fetchShipmentByCustomerName(customerName);
    await generatePdf(shipments, customerName);
});