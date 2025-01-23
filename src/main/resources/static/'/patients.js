// Get the patients-list element
const patientsList = document.getElementById('patients-list');

// Fetch data from the /api/patients endpoint
fetch('/api/patients')
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json(); // Parse JSON response
    })
    .then(data => {
        data.forEach(patient => {
            // Create a new list item for each patient
            const listItem = document.createElement('li');
            listItem.textContent = `Patient ID: ${patient.patientId}, Name: ${patient.name}`; // Customize text content
            listItem.className = 'list-group-item'; // Add Bootstrap class for styling
            patientsList.appendChild(listItem); // Append the list item to the patients-list
        });
    })
    .catch(error => {
        console.error('Error fetching data:', error);
        // Display error message in the UI
        const errorMessage = document.createElement('li');
        errorMessage.textContent = 'Error fetching patient data.';
        errorMessage.className = 'list-group-item list-group-item-danger';
        patientsList.appendChild(errorMessage);
    });