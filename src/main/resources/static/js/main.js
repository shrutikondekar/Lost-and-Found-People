const API = "http://localhost:8080/api/items";

// GET ALL ITEMS
async function loadItems() {
    async function loadItems() {
        try {
            const res = await fetch('/api/items');

            if (!res.ok) {
                console.warn("⚠ API returned an error:", res.status);
                return;
            }

            const data = await res.json();

            if (!Array.isArray(data)) {
                console.error("❌ Expected array, got:", data);
                return;
            }

            data.forEach(item => console.log(item));

        } catch (err) {
            console.error("💥 Fetch failed:", err);
        }
    }


    document.getElementById("items").innerHTML = html;
}

// ADD ITEM
async function addItem() {
    const item = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        status: document.getElementById("status").value
    };

    await fetch(API, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(item)
    });

    loadItems(); // reload after adding
}

loadItems();
