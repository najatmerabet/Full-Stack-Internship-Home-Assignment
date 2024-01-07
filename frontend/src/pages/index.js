// pages/index.js
import { useState } from 'react';
import UploadButton from '@/components/UploadButton';
import ProcessButton from '@/components/ProcessButton';
import ResultsTables from '@/components/ResultsTables';

export default function Home() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [processingResults, setProcessingResults] = useState(null);

  const handleFileSelect = (file) => {
    setSelectedFile(file);
  };

  const handleProcess = async () => {
    // Implement logic to send the file to the server and get processing results
    const formData = new FormData();
    formData.append('file', selectedFile);

    try {
      const response = await fetch('/api/process', {
        method: 'POST',
        body: formData,
      });

      if (response.ok) {
        const results = await response.json();
        // Set the processing results state
        setProcessingResults(results);
      } else {
        console.error('Failed to process the file');
      }
    } catch (error) {
      console.error('Error processing the file:', error);
    }
  };

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <div>
        <h2>Interface-1: Upload Button</h2>
        <UploadButton onFileSelect={handleFileSelect} />
      </div>

      {selectedFile && (
        <div>
          <h2>Interface-2: Process Button</h2>
          <ProcessButton onClick={handleProcess} />
        </div>
      )}

      {processingResults && (
        <div>
          <h2>Interface-3: Results Tables</h2>
          <ResultsTables
            employeeData={processingResults.employeeData}
            jobSummary={processingResults.jobSummary}
          />
        </div>
      )}
    </main>
  );
}
