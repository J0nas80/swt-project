import { useState } from 'react';
import axios from 'axios';
import TopNav from '../components/TopNav';
import BottomNav from '../components/BottomNav';

export default function InseratForm() {
  const [formData, setFormData] = useState({
    title: '',
    type: '',
    area: '',
    roomCount: '',
    description: '',
    rentCold: '',
    availableFrom: '',
    street: '',
    houseNumber: '',
    postalCode: '',
    city: '',
    img: null
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === 'img') {
      setFormData({ ...formData, image: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = new FormData();
    for (const key in formData) {
      data.append(key, formData[key]);
    }

    try {
      const response = await axios.post(
        'http://localhost:8080/api/estate', // Replace with actual backend endpoint
        data,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        }
      );

      console.log('Inserat erfolgreich eingereicht:', response.data);
      alert('Inserat wurde erfolgreich eingereicht!');
      setFormData({
        title: '',
        type: '',
        area: '',
        roomCount: '',
        description: '',
        rentCold: '',
        availableFrom: '',
        street: '',
        houseNumber: '',
        postalCode: '',
        city: '',
        img: null
      });
    } catch (error) {
      console.error('Fehler beim Erstellen des Inserats:', error);
      alert('Erstellen fehlgeschlagen.');
    }
  };

  return (
    <div style={styles.container}>
      <TopNav />
      <h2 style={styles.heading}>Inserat erstellen</h2>
      <form onSubmit={handleSubmit} style={styles.form}>
        <input
          type="text"
          name="title"
          placeholder="Titel"
          value={formData.title}
          onChange={handleChange}
          style={styles.input}
          required
        />
        <select name="type" value={formData.type} onChange={handleChange} style={styles.input} required>
          <option value="">Wohnform wählen</option>
          <option value="CoHousing">CoHousing</option>
          <option value="Mehrgenerationwohnung">Mehrgenerationwohnung</option>
          <option value="Mikroappartement">Mikroappartement</option>
          <option value="Baugruppe">Baugruppe</option>
        </select>

        <input type="number" name="area" placeholder="Fläche (m²)" value={formData.area} onChange={handleChange} style={styles.input} required />
        <input type="number" name="roomCount" placeholder="Anzahl Zimmer" value={formData.roomCount} onChange={handleChange} style={styles.input} required />
        <textarea name="description" placeholder="Beschreibung" value={formData.description} onChange={handleChange} style={styles.textarea} required />
        <input type="number" name="rentCold" placeholder="Kaltmiete (€)" value={formData.rentCold} onChange={handleChange} style={styles.input} required />
        <input type="date" name="availableFrom" value={formData.availableFrom} onChange={handleChange} style={styles.input} required />
        <input type="text" name="street" placeholder="Straße" value={formData.street} onChange={handleChange} style={styles.input} required />
        <input type="text" name="houseNumber" placeholder="Hausnummer" value={formData.houseNumber} onChange={handleChange} style={styles.input} required />
        <input type="text" name="postalCode" placeholder="PLZ" value={formData.postalCode} onChange={handleChange} style={styles.input} required />
        <input type="text" name="city" placeholder="Stadt" value={formData.city} onChange={handleChange} style={styles.input} required />
        <input type="file" name="img" accept="image/*" onChange={handleChange} style={styles.input} />
        <button
          type="submit"
          style={styles.button}
          onMouseOver={(e) => (e.target.style.backgroundColor = styles.buttonHover.backgroundColor)}
          onMouseOut={(e) => (e.target.style.backgroundColor = styles.button.backgroundColor)}
        >
          Inserat einreichen
        </button>
      </form>
      <BottomNav />
    </div>
  );
}


const styles = {
  container: {
    maxWidth: '600px',
    margin: '40px auto',
    padding: '20px',
    border: '1px solid #ddd',
    borderRadius: '10px',
    background: '#f9f9f9'
  },
  heading: {
    textAlign: 'center',
    marginBottom: '20px'
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '15px'
  },
  input: {
    padding: '10px',
    fontSize: '16px',
    border: '1px solid #bbb',
    borderRadius: '50px'
  },
  textarea: {
    padding: '10px',
    fontSize: '16px',
    border: '1px solid #bbb',
    borderRadius: '50px',
    resize: 'vertical'
  },
  button: {
    padding: '10px',
    backgroundColor: 'black',
    color: 'white',
    fontSize: '16px',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer'
  },
  buttonHover: {
    backgroundColor: '#0056b3'
  }
};