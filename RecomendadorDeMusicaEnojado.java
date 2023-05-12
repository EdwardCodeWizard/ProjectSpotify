import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

public class RecomendadorDeMusicaEnojado {

  private static final String CLIENT_ID = "TU_CLIENT_ID";
  private static final String CLIENT_SECRET = "TU_CLIENT_SECRET";
  private static final String REDIRECT_URI = "TU_REDIRECT_URI";
  private static final String QUERY = "angry";

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
      
      // Realizamos la búsqueda de una canción
      SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(QUERY)
          .market("ES")
          .limit(1)
          .build();
      Paging<Track> paging = searchTracksRequest.execute();
      Track[] tracks = paging.getItems();
      if (tracks.length > 0) {
        System.out.println("Te recomendamos escuchar: " + tracks[0].getName() + " de " + tracks[0].getArtists()[0].getName());
        System.out.println("Escúchala aquí: " + tracks[0].getExternalUrls().get("spotify"));
      } else {
        System.out.println("No se encontraron canciones para este estado de ánimo");
      }

    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error al conectar con la API de Spotify");
    }
  }
}