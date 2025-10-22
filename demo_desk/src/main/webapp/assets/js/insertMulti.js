const tableBody = document.getElementById('tableBody');
const addRowBtn = document.getElementById('addRowBtn');
const removeRowBtn = document.getElementById('removeRowBtn');
const rowCountInput = document.getElementById('rowCount');

// Update hidden input with current row count
function updateRowCount() {
    rowCountInput.value = tableBody.rows.length;
}

// Helper function to auto-expand textarea height
function autoExpandTextarea(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
}

// Helper to create input td
function createInputCell(type = 'text', name = '', placeholder = '', value = '') {
    const td = document.createElement('td');
    const input = document.createElement('input');
    input.type = type;
    input.name = name;
    input.placeholder = placeholder;
	input.required = true;
    input.autocomplete = "off";
    if (value) input.value = value;
    td.appendChild(input);
    return td;
}

// Helper to create textarea td for addresses
function createTextareaCell(name = '', placeholder = '') {
    const td = document.createElement('td');
    const textarea = document.createElement('textarea');
    textarea.name = name;
    textarea.placeholder = placeholder;
    textarea.classList.add('address-textarea');
    textarea.rows = 1;
	textarea.required = true;
    textarea.autocomplete = "off";

    // Auto expand on input
    textarea.addEventListener('input', () => autoExpandTextarea(textarea));

    td.appendChild(textarea);
    return td;
}

// Helper to create select dropdown td for risk surcharge
function createSelectCell(name = '') {
    const td = document.createElement('td');
    const select = document.createElement('select');
    select.name = name;
	select.required = true;

    const options = [
        { value: 'No Risk', text: 'No Risk' },
        { value: 'Carrier Risk', text: 'Carrier Risk' },
        { value: 'Owner Risk', text: 'Owner Risk' }
    ];

    options.forEach(opt => {
        const option = document.createElement('option');
        option.value = opt.value;
        option.textContent = opt.text;
        select.appendChild(option);
    });

    td.appendChild(select);
    return td;
}

// Helper to create select dropdown td for Content column
function createContentSelectCell(name = '') {
    const td = document.createElement('td');
    const select = document.createElement('select');
    select.name = name;
	select.required = true;

    const options = [
        { value: 'Document', text: 'Document' },
        { value: 'Non Document', text: 'Non Document' }
    ];

    options.forEach(opt => {
        const option = document.createElement('option');
        option.value = opt.value;
        option.textContent = opt.text;
        select.appendChild(option);
    });

    td.appendChild(select);
    return td;
}

// Function to create a new row with inputs
function createRow(index) {
    const tr = document.createElement('tr');

    // Sr No (readonly)
    const tdSrNo = document.createElement('td');
    tdSrNo.textContent = index + 1;
    tdSrNo.setAttribute('aria-label', 'Serial Number');
    tr.appendChild(tdSrNo);

    // Current date in yyyy-mm-dd format
    const today = new Date().toISOString().split('T')[0];

    // Append columns in sequence:
    // Sr No, C Note, Date, Consignor's Name, Mob No., Consignee's Name, Consignee's Address, Mob No., Content, Declared Value, No. of Pieces, Actual Weight, Dimensions, Destination, Pincode, RISK SURCHARGE

    // C Note with min width class
    const tdCNote = createInputCell('text', `cNote_${index}`, 'C Note');
    tdCNote.classList.add('cnote');
    tr.appendChild(tdCNote);

    // Date with default current date
    const tdDate = document.createElement('td');
    const dateInput = document.createElement('input');
    dateInput.type = 'date';
    dateInput.name = `date_${index}`;
    dateInput.value = today;
    dateInput.autocomplete = "off";
    tdDate.appendChild(dateInput);
    tr.appendChild(tdDate);

    // Consignor's Name
    tr.appendChild(createInputCell('text', `consignorName_${index}`, "Consignor's Name"));

    // Consignor's Mob No.
    tr.appendChild(createInputCell('text', `consignorMob_${index}`, "Mob No."));

    // Consignee's Name
    tr.appendChild(createInputCell('text', `consigneeName_${index}`, "Consignee's Name"));

    // Consignee's Address (textarea with auto expand)
    const tdConsigneeAddress = createTextareaCell(`consigneeAddress_${index}`, "Consignee's Address");
    tr.appendChild(tdConsigneeAddress);

    // Consignee's Mob No.
    tr.appendChild(createInputCell('text', `consigneeMob_${index}`, "Mob No."));

    // Content (dropdown)
    tr.appendChild(createContentSelectCell(`content_${index}`));

    // Declared Value (number)
    tr.appendChild(createInputCell('number', `declaredValue_${index}`, "Declared Value"));

    // No. of Pieces (number) default 1
    tr.appendChild(createInputCell('number', `pieces_${index}`, "No. of Pieces", '1'));

    // Actual Weight (number)
    tr.appendChild(createInputCell('number', `actualWeight_${index}`, "Actual Weight"));

    // Dimensions (text) default "1 * 1 * 1"
    tr.appendChild(createInputCell('text', `dimensions_${index}`, "Dimensions", '1 * 1 * 1'));

    // Destination
    tr.appendChild(createInputCell('text', `destination_${index}`, "Destination"));

    // Pincode
    tr.appendChild(createInputCell('text', `pincode_${index}`, "Pincode"));

    // Risk Surcharge (select dropdown)
    tr.appendChild(createSelectCell(`riskSurcharge_${index}`));

    return tr;
}

// Add a new row and update row count
function addRow() {
    const rowCount = tableBody.rows.length;
    const newRow = createRow(rowCount);
    tableBody.appendChild(newRow);
    updateRowCount();
}

// Remove last row and update row count
function removeRow() {
    const rowCount = tableBody.rows.length;
    if (rowCount > 1) {
        tableBody.deleteRow(rowCount - 1);
        updateRowCount();
    }
}

// Initialize with one row and update row count
addRow();
updateRowCount();

addRowBtn.addEventListener('click', addRow);
removeRowBtn.addEventListener('click', removeRow);