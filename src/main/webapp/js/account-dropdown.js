document.addEventListener("DOMContentLoaded", function() {
    var accountDropdown = document.getElementById("accountDropdown");

    // Mostrar u ocultar el menú desplegable al hacer clic en el ícono de la cuenta
    accountDropdown.addEventListener("click", function() {
        toggleDropdown();
    });

    // Cerrar el menú cuando se hace clic fuera de él
    window.addEventListener("click", function(event) {
        if (!accountDropdown.contains(event.target)) {
            closeDropdown();
        }
    });

    function toggleDropdown() {
        var dropdownContent = accountDropdown.querySelector(".account-dropdown-content");
        dropdownContent.style.display = (dropdownContent.style.display === "block") ? "none" : "block";
    }

    function closeDropdown() {
        var dropdownContent = accountDropdown.querySelector(".account-dropdown-content");
        dropdownContent.style.display = "none";
    }
});
