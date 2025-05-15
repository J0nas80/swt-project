export default function WohnungForm({ onBack }) {
    return (
      <form>
        <h3>Wohnung inserieren</h3>
  
        <label>
          Titel: <input type="text" name="titel" required />
        </label>
        <br />
  
        <label>
          Beschreibung: <textarea name="beschreibung" />
        </label>
        <br />
  
        <label>
          Adresse: <input type="text" name="adresse" />
        </label>
        <br />
  
        <label>
          Stadt / PLZ: <input type="text" name="stadt_plz" />
        </label>
        <br />
  
        <label>
          Miete (€): <input type="number" name="miete" />
        </label>
        <br />
  
        <label>
          Fläche (m²): <input type="number" name="flaeche" />
        </label>
        <br />
  
        <label>
          Verfügbar ab: <input type="date" name="verfuegbar_ab" />
        </label>
        <br />
  
        <label>
          Bilder (optional): <input type="file" name="bilder" accept="image/*" />
        </label>
        <br />
  
        <button type="button" onClick={onBack}>← Zurück</button>
        <button type="submit">Inserat speichern</button>
      </form>
    );
  }