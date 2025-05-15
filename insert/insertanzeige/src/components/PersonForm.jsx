export default function PersonForm({ onBack }) {
  return (
    <form>
      <h3>Person inserieren</h3>
      
      <label>
        Name: <input type="text" name="name" required />
      </label>
      <br />

      <label>
        Alter: <input type="number" name="alter" />
      </label>
      <br />

      <label>
        Beschreibung: <textarea name="beschreibung" />
      </label>
      <br />

      <label>
        Interessen / Hobbys: <input type="text" name="interessen" />
      </label>
      <br />

      <label>
        Gesuch (WG / Appartement): 
        <select name="gesuch" required>
          <option value="">Bitte wählen</option>
          <option value="WG">WG</option>
          <option value="Appartement">Appartement</option>
        </select>
      </label>
      <br />

      <label>
        Stadt: <input type="text" name="stadt" />
      </label>
      <br />
      
      <button type="button" onClick={onBack}>← Zurück</button>
      <button type="submit">Inserat speichern</button>
    </form>
  );
}