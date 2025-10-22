const searchForm = document.getElementById('searchForm');
const shipmentDateInput = document.getElementById('shipmentDate');
const pdfContainer = document.getElementById('pdfContainer');
const pdfPreview = document.getElementById('pdfPreview');
const downloadPdfBtn = document.getElementById('downloadPdfBtn');

let currentPdfBlobUrl = null;

// Generate PDF from shipment data
async function generatePdf(shipments, selectedDate) {
	const { jsPDF } = window.jspdf;
	const doc = new jsPDF();

	doc.setFontSize(18);
	doc.text(`Shipments for Date: ${selectedDate}`, 14, 22);

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
	shipments.sort((a, b) => (a.cNote || '').localeCompare(b.cNote || ''));

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

// Utility function to format date
function formatDateToDDMonYY(dateStr) {
	const date = new Date(dateStr);
	return date.toLocaleDateString("en-GB", {
		day: "2-digit",
		month: "short",
		year: "2-digit"
	}).replace(/ /g, "-");
}

let shipmentData = [];
// Fetch shipment data by selected date
async function fetchShipmentByDate(selectedDate) {
	try {
		const response = await fetch(`/demo_desk/searchByDate?date=${encodeURIComponent(selectedDate)}`);
		if (!response.ok) throw new Error('Failed to fetch shipment data');
		const shipments = await response.json();
		
		shipmentData = shipments;
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
	const selectedDate = shipmentDateInput.value;
	if (!selectedDate) return;

	const shipments = await fetchShipmentByDate(selectedDate);
	await generatePdf(shipments, selectedDate);
});
