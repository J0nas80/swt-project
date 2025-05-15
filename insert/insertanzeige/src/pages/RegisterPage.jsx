export default function RegisterPage() {
    return (
      <div>
        <h2>Registrieren</h2>
        <form>
          <label>Email: <input type="email" /></label><br />
          <label>Passwort: <input type="password" /></label><br />
          <label>Passwort bestätigen: <input type="password" /></label><br />
          <button type="submit">Registrieren</button>
        </form>
      </div>
    );
  }