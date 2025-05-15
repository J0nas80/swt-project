import { useState } from "react";
import ListingFormSelector from "../components/ListingFormSelector";
import WohnungForm from "../components/WohnungForm";
import PersonForm from "../components/PersonForm";

export default function AddListingPage() {
  const [selectedType, setSelectedType] = useState(null);

  const handleBack = () => setSelectedType(null);

  return (
    <div>
      {!selectedType && <ListingFormSelector onSelect={setSelectedType} />}
      {selectedType === "wohnung" && <WohnungForm onBack={handleBack}/>}
      {selectedType === "person" && <PersonForm onBack={handleBack}/>}
    </div>
  );
}