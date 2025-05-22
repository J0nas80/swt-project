import { useState } from 'react';
import TopNav from '../components/TopNav';
import BottomNav from '../components/BottomNav';

export default function InseratForm() {
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    price: '',
    address: '',
    city: '',
    area: '',
    access_from: '',
    image: null
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === 'image') {
      setFormData({ ...formData, image: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Inserat submitted:', formData);
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
        <textarea
          name="description"
          placeholder="Beschreibung"
          value={formData.description}
          onChange={handleChange}
          style={styles.textarea}
          required
        />
        <input
          type="number"
          name="price"
          placeholder="Preis (€)"
          value={formData.price}
          onChange={handleChange}
          style={styles.input}
          required
        />
        <input
          type="text"
          name="address"
          placeholder="Adresse"
          value={formData.address}
          onChange={handleChange}
          style={styles.input}
          required
        />
        <input
          type="text"
          name="city"
          placeholder="Stadt"
          value={formData.city}
          onChange={handleChange}
          style={styles.input}
          required
        />
        <input
          type="text"
          name="area"
          placeholder="Fläche (z. B. 120 m²)"
          value={formData.area}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="date"
          name="access_from"
          placeholder="Zugang ab"
          value={formData.access_from}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="file"
          name="image"
          accept="image/*"
          onChange={handleChange}
          style={styles.input}
        />
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