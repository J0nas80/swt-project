export default function ListingFormSelector({ onSelect }) {
    return (
      <div>
        <h2>Was möchtest du inserieren?</h2>
        <button onClick={() => onSelect('wohnung')}>Wohnung</button>
        <button onClick={() => onSelect('person')}>Person</button>
      </div>
    );
  }