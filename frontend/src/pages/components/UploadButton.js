// components/UploadButton.js
import React from 'react';

const UploadButton = ({ onFileSelect }) => {
  const handleFileSelect = (e) => {
    const file = e.target.files[0];
    onFileSelect(file);
  };

  return (
    <div>
      <input type="file" onChange={handleFileSelect} />
    </div>
  );
};

export default UploadButton;
