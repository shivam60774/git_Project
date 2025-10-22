let customers = []; // Working copy: ["John Doe", "Jane Smith", ...] (array of strings)
let isDirty = false; // Track changes to show save button
let editMode = false; // Track edit mode for removes

// Fetch customers from backend and display (expects array of strings)
async function loadCustomers() {
    try {
        const addBtn = document.getElementById("addCustomerBtn");
        addBtn.disabled = true; // Loading state
        addBtn.textContent = "Loading...";

        const response = await fetch('/demo_desk/getCustomers');
        if (!response.ok) throw new Error("Failed to fetch customers");
        customers = await response.json(); // Now array of strings: ["John Doe", ...]
        isDirty = false; // Fresh load, no changes
        renderCustomers();
    } catch (err) {
        console.error(err);
        alert("Error loading customers: " + err.message);
    } finally {
        const addBtn = document.getElementById("addCustomerBtn");
        addBtn.disabled = false;
        addBtn.textContent = "Add Customer";
    }
}

// Render customers in UI (name only, plus hidden remove btn)
function renderCustomers() {
    const grid = document.getElementById("customersGrid");
    if (!grid) return; // Safety

    grid.innerHTML = ""; // Clear existing

    customers.forEach((customer) => {
        const box = document.createElement("div");
        box.className = "customer-box";
        box.innerHTML = `
            <span>${customer || "Unnamed"}</span>
            <div class="remove-btn" onclick="removeCustomer('${customer}')">-</div>
        `;
        grid.appendChild(box);
    });

    updateSaveButtonVisibility();
}

// Add new customer (push string name)
document.getElementById("addCustomerBtn").addEventListener("click", () => {
    const newName = prompt("Enter customer name:");
    if (newName && newName.trim()) {
        customers.push(newName.trim()); // Just the string
        isDirty = true;
        renderCustomers();
    }
});

// Toggle edit mode for removes
document.getElementById("removeCustomerBtn").addEventListener("click", () => {
    editMode = !editMode;
    const container = document.getElementById("customersContainer");
    container.classList.toggle("edit-mode", editMode);
    const btn = document.getElementById("removeCustomerBtn");
    btn.textContent = editMode ? "Cancel Remove" : "Remove Customer";
});

// Remove by name (filter the array)
function removeCustomer(name) {
    customers = customers.filter(c => c !== name); // Remove exact match
    isDirty = true;
    renderCustomers();
}

// Save updated customers back to backend (send array of strings)
document.getElementById("saveChangesBtn").addEventListener("click", async () => {
    try {
        const saveBtn = document.getElementById("saveChangesBtn");
        saveBtn.disabled = true;
        saveBtn.textContent = "Saving...";

        const response = await fetch("/demo_desk/updateCustomers", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(customers) // Array of strings: ["John Doe", ...]
        });
        if (!response.ok) throw new Error("Failed to save customers");
        alert("Customers updated successfully!");
        isDirty = false;
        editMode = false;
        document.getElementById("removeCustomerBtn").textContent = "Remove Customer";
        document.getElementById("customersContainer").classList.remove("edit-mode");
        // Optionally re-fetch to sync with backend
        // loadCustomers();
    } catch (err) {
        console.error(err);
        alert("Error saving customers: " + err.message);
    } finally {
        const saveBtn = document.getElementById("saveChangesBtn");
        saveBtn.disabled = false;
        saveBtn.textContent = "Save Changes";
        updateSaveButtonVisibility();
    }
});

// Show/hide save button based on changes
function updateSaveButtonVisibility() {
    const saveBtn = document.getElementById("saveChangesBtn");
    if (isDirty) {
        saveBtn.style.display = "inline-block";
    } else {
        saveBtn.style.display = "none";
    }
}

// Load on page start
document.addEventListener("DOMContentLoaded", loadCustomers);
