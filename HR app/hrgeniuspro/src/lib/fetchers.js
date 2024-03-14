const SERVER_URL = 'http://localhost:5000'

const fetchV1WithAuth = async (url, options) => {
    const headers = {
        'Content-Type': 'application/json',
    }

    const rawResponse = await fetch(`${SERVER_URL}/api/v1/${url}`, {
        ...options,
        credentials: 'include',
        headers,
    })

    return rawResponse
}

const fetchV1FormDataWithAuth = async (
    url,
    options
) => {
    const rawResponse = await fetch(`${SERVER_URL}/api/v1/${url}`, {
        ...options,
        credentials: 'include',
    })

    return rawResponse
}

export { fetchV1WithAuth, fetchV1FormDataWithAuth }