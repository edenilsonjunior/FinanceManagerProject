export const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

export const submitGet = async (servletUrl) => {
    try {
        const response = await fetch(contextPath + servletUrl);
        return await response.json();
    } catch (error) {
        console.error(error);
    }
}