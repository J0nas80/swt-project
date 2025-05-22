import React from 'react';
import TopNav from '../components/TopNav';
import BottomNav from '../components/BottomNav';

import image from "../assets/home.webp";

export default function UserData() {
  const user = {
    name: 'John Doe',
    vorname: 'John',
    nachname: 'Doe',
    geschlecht: 'Männlich',
    alter: 30,
    beruf: 'Software Engineer',
    adresse: '1234 Main St, City, Country',
  };

  return (
    <div>
      <TopNav />
      <div style={styles.container}>
        <div style={styles.profile}>
          <img src={image} alt="User" style={styles.profileImage} />
          <h2>{user.name}</h2>
        </div>
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.tableHeader}>Category</th>
              <th style={styles.tableHeader}>Information</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td style={styles.tableCell}>Vorname</td>
              <td style={styles.tableCell}>{user.vorname}</td>
            </tr>
            <tr>
              <td style={styles.tableCell}>Nachname</td>
              <td style={styles.tableCell}>{user.nachname}</td>
            </tr>
            <tr>
              <td style={styles.tableCell}>Geschlecht</td>
              <td style={styles.tableCell}>{user.geschlecht}</td>
            </tr>
            <tr>
              <td style={styles.tableCell}>Alter</td>
              <td style={styles.tableCell}>{user.alter}</td>
            </tr>
            <tr>
              <td style={styles.tableCell}>Beruf</td>
              <td style={styles.tableCell}>{user.beruf}</td>
            </tr>
            <tr>
              <td style={styles.tableCell}>Adresse</td>
              <td style={styles.tableCell}>{user.adresse}</td>
            </tr>
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
