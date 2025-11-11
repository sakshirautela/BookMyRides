import React, { useEffect, useState } from 'react';

export default function ProfileManager() {
    const [profile, setProfile] = useState();

    const token = localStorage.getItem('authToken');

    const getProfiles = async () => {
        if (!token) {
            console.warn('No auth token found; skipping profiles fetch.');
            return;
        }

        try {
            const response = await fetch('/api/profiles', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                const text = await response.text();
                throw new Error(`Fetch failed: ${response.status} ${response.statusText} - ${text}`);
            }

            const data = await response.json();
            setProfiles(data);
        } catch (error) {
            console.error('Error fetching profiles:', error);
        }
    };

    useEffect(() => {
        getProfiles();
    }, []);

    return (
        <div>
            <h1>Profile Manager</h1>
            {profiles.length === 0 ? (
                <p>No profiles found.</p>
            ) : (
                <ul>
                    {profiles.map((p, i) => (
                        <li key={p.id ?? i}>{p.name ?? JSON.stringify(p)}</li>
                    ))}
                </ul>
            )}
        </div>
    );
}