import React, { useEffect, useState } from 'react';
import axios from 'axios';
import TopNav from '../components/TopNav';
import BottomNav from '../components/BottomNav';

import image from "../assets/profile.jpg";

export default function UserData() {
  const [user, setUser] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    const token = localStorage.getItem("token").trim();
    if (!token) {
      setError("Nicht eingeloggt.");
      return;
    }
    axios.get(`http://${process.env.BACKEND_HOST}:${process.env.BACKEND_PORT}/api/user/me`, {
      headers: {
        Authorization: `Bearer ${token}`,
      }
    })
    .then(response => {
      setUser(response.data);
    })
    .catch(err => {
      console.error(err);
      setError("Fehler beim Laden der Benutzerdaten.");
    });
  }, []);

  if (error) {
    return (
      <div>
        <TopNav />
        <p style={{ paddingTop: "200px", color: "red" }}>{error}</p>
        <BottomNav title="UserData" />
      </div>
    );
  }

  if (!user) {
    return (
      <div>
        <TopNav />
        <p style={{ padding: "20px" }}>Lade Benutzerdaten...</p>
        <BottomNav title="UserData" />
      </div>
    );
  }

  return (
    <div>
      <TopNav />
      <div style={styles.container}>
        <div style={styles.profile}>
          <img src={image} alt="User" style={styles.profileImage} />
          <h2>{`${user.firstName} ${user.name}`}</h2>
        </div>
        <table style={styles.table}>
          <tbody>
            <tr><td style={styles.tableCell}>Vorname</td><td style={styles.tableCell}>{user.firstName}</td></tr>
            <tr><td style={styles.tableCell}>Nachname</td><td style={styles.tableCell}>{user.name}</td></tr>
            <tr><td style={styles.tableCell}>Geburtsdatum</td><td style={styles.tableCell}>{user.dob}</td></tr>
            <tr><td style={styles.tableCell}>Geschlecht</td><td style={styles.tableCell}>{user.gender}</td></tr>
            <tr><td style={styles.tableCell}>E-Mail</td><td style={styles.tableCell}>{user.email}</td></tr>
            <tr><td style={styles.tableCell}>Telefon</td><td style={styles.tableCell}>{user.phoneNumber}</td></tr>
          </tbody>
        </table>
      </div>
      <BottomNav title="UserData" />
    </div>
  );
}

const styles = {
  
  container: {
    padding: '20px',
    textAlign: 'center',
    marginTop: "70px"
  },
  profile: {
    marginBottom: '20px',
  },
  profileImage: {
    borderRadius: '50%',
    width: '150px',
    height: '150px',
  },
  table: {
    margin: '0 auto',
    borderCollapse: 'collapse',
    width: '80%',
    border: '1px solid #ddd',
  },
  tableHeader: {
    backgroundColor: '#f4f4f4',
    padding: '10px',
    textAlign: 'left',
    border: '3px solid #ddd',
  },
  tableCell: {
    padding: '10px',
    border: '3px solid #ddd',
  },

};
