import { useState, useEffect } from 'react'
import { apiService } from './services/api'

function App() {
  const [backendStatus, setBackendStatus] = useState(null)
  const [healthStatus, setHealthStatus] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    // Test backend connection on component mount
    testBackendConnection()
  }, [])

  const testBackendConnection = async () => {
    setLoading(true)
    setError(null)
    
    try {
      // Test both endpoints
      const [testResponse, healthResponse] = await Promise.all([
        apiService.test(),
        apiService.health()
      ])
      
      setBackendStatus(testResponse.data)
      setHealthStatus(healthResponse.data)
    } catch (err) {
      setError(err.message || 'Failed to connect to backend')
      console.error('Backend connection error:', err)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100">
      <div className="container mx-auto px-4 py-16">
        {/* Header */}
        <div className="text-center mb-12">
          <h1 className="text-5xl font-bold text-gray-800 mb-4">
            🍳 Smart Recipe Manager
          </h1>
          <p className="text-xl text-gray-600">
            Full-Stack Application with React + Spring Boot
          </p>
        </div>

        {/* Status Card */}
        <div className="max-w-2xl mx-auto">
          <div className="bg-white rounded-2xl shadow-xl p-8">
            <h2 className="text-2xl font-bold text-gray-800 mb-6 flex items-center">
              <span className="mr-3">🔌</span>
              Backend Connection Status
            </h2>

            {loading && (
              <div className="text-center py-8">
                <div className="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-indigo-600"></div>
                <p className="mt-4 text-gray-600">Testing backend connection...</p>
              </div>
            )}

            {error && (
              <div className="bg-red-50 border-l-4 border-red-500 p-4 rounded">
                <div className="flex items-center">
                  <span className="text-2xl mr-3">❌</span>
                  <div>
                    <p className="font-bold text-red-800">Connection Failed</p>
                    <p className="text-red-600 text-sm mt-1">{error}</p>
                    <p className="text-red-500 text-xs mt-2">
                      Make sure your Spring Boot backend is running on port 8080
                    </p>
                  </div>
                </div>
                <button
                  onClick={testBackendConnection}
                  className="mt-4 bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition"
                >
                  Retry Connection
                </button>
              </div>
            )}

            {!loading && !error && backendStatus && (
              <div className="space-y-4">
                {/* Test Endpoint Response */}
                <div className="bg-green-50 border-l-4 border-green-500 p-4 rounded">
                  <div className="flex items-center mb-2">
                    <span className="text-2xl mr-3">✅</span>
                    <p className="font-bold text-green-800">Backend Connected!</p>
                  </div>
                  <div className="mt-3 bg-white p-3 rounded border border-green-200">
                    <p className="text-sm font-mono text-gray-700">
                      <strong>Status:</strong> {backendStatus.status}
                    </p>
                    <p className="text-sm font-mono text-gray-700">
                      <strong>Message:</strong> {backendStatus.message}
                    </p>
                    <p className="text-sm font-mono text-gray-700">
                      <strong>Developer:</strong> {backendStatus.developer}
                    </p>
                    <p className="text-sm font-mono text-gray-700">
                      <strong>Timestamp:</strong> {backendStatus.timestamp}
                    </p>
                  </div>
                </div>

                {/* Health Endpoint Response */}
                <div className="bg-blue-50 border-l-4 border-blue-500 p-4 rounded">
                  <div className="flex items-center mb-2">
                    <span className="text-2xl mr-3">💚</span>
                    <p className="font-bold text-blue-800">Health Check</p>
                  </div>
                  <div className="mt-3 bg-white p-3 rounded border border-blue-200">
                    <p className="text-sm font-mono text-gray-700">
                      <strong>Status:</strong> {healthStatus.status}
                    </p>
                    <p className="text-sm font-mono text-gray-700">
                      <strong>Service:</strong> {healthStatus.service}
                    </p>
                  </div>
                </div>

                {/* Success Message */}
                <div className="text-center mt-6 p-4 bg-indigo-50 rounded-lg">
                  <p className="text-indigo-800 font-semibold">
                    🎉 Frontend and Backend are connected successfully!
                  </p>
                  <p className="text-indigo-600 text-sm mt-2">
                    You're ready to build amazing features!
                  </p>
                </div>
              </div>
            )}
          </div>

          {/* Tech Stack Info */}
          <div className="mt-8 text-center text-gray-600">
            <p className="text-sm">
              <strong>Tech Stack:</strong> React + Vite + Tailwind CSS + Axios + Spring Boot
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}

export default App