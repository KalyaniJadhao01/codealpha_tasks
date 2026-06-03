async function shortenUrl() {

    const input = document.getElementById("urlInput");
    const url = input.value.trim();

    const customInput = document.getElementById("customCodeInput");
    const customCode = customInput.value.trim();

    if (!url) {
        alert("Please enter URL");
        return;
    }

    try {

        const response = await fetch("/api/urls", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ url: url,
                                    customCode: customCode
                                 })
        });

        const data = await response.json();

        if (!response.ok) {
            // show backend validation message
            alert(data.message || "Request failed");
            return;
        }

        const result = document.getElementById("result");
        const shortLink = document.getElementById("shortLink");

        shortLink.href = data.shortUrl;
        shortLink.innerText = data.shortUrl;

        result.classList.remove("hidden");

    } catch (error) {
        alert("Server not reachable");
    }
}