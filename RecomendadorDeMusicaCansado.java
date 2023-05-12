import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

public class RecomendadorDeMusicaCansado {
    // Autenticamos con la API de Spotify
    private static final String CLIENT_ID = "TU_CLIENT_ID";
    private static final String CLIENT_SECRET = "TU_CLIENT_SECRET";
    private static final String REDIRECT_URI = "TU_REDIRECT_URI";
    private static final String QUERY = "relaxing";

    public void recomendarCancion() {
        try {
            // Conectamos con la API de Spotify
            SpotifyApi spotifyApi = new SpotifyApi.Builder()
                    .setClientId(CLIENT_ID)
                    .setClientSecret(CLIENT_SECRET)
                    .setRedirectUri(REDIRECT_URI)
                    .build();

            // Obtenemos un token de acceso a la API de Spotify
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            // Buscamos canciones relajantes en Spotify
            SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(QUERY).limit(10).build();
            Paging<Track> trackPaging = searchTracksRequest.execute();
            Track[] tracks = trackPaging.getItems();

            // Elegimos una canción al azar entre las encontradas
            int index = (int) (Math.random() * tracks.length);
            Track track = tracks[index];

            // Mostramos el enlace de la canción
            System.out.println("Esta canción te ayudará a relajarte: " + track.getExternalUrls().get("spotify"));

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error al conectar con la API de Spotify");
        }
    }
}
