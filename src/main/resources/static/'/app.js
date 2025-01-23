// const patientsList = document.getElementById('patients-list');
//
// fetch('/api/patients')
//     .then(response => response.json()) // Parse JSON response
//     .then(data => {
//         data.forEach(patient => {
//             const listItem = document.createElement('li');
//             listItem.textContent = `Patient ID: ${patient.patientId}, Name: ${patient.name}`; // Customize text content
//             patientsList.appendChild(listItem);
//         });
//     })
//     .catch(error => console.error('Error fetching data:', error));
// Get the patients-list element
