 /* // Set last login date and time dynamically */
    function updateLastLogin() {
      const lastLoginEl = document.getElementById('last-login');
      const now = new Date();
      const optionsDate = { year: 'numeric', month: '2-digit', day: '2-digit' };
      const optionsTime = { hour: '2-digit', minute: '2-digit', second: '2-digit' };
      const dateStr = now.toLocaleDateString(undefined, optionsDate);
      const timeStr = now.toLocaleTimeString(undefined, optionsTime);
      lastLoginEl.textContent = `Last login: ${dateStr} at ${timeStr}`;
    }
    updateLastLogin();
     /* Optionally update every minute */
    setInterval(updateLastLogin, 60000);