import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

public class RecomendadorDeMusicaFeliz {

  private static final String CLIENT_ID = "TU_CLIENT_ID";
  private static final String CLIENT_SECRET = "TU_CLIENT_SECRET";
  private static final String REDIRECT_URI = "TU_REDIRECT_URI";
  private static final String QUERY = "happy";

  public void recomendarCancion() {
    try {
      // Conectamos con la API de Spotify
      SpotifyApi spotifyApi = new SpotifyApi.Builder()
              .setClientId(CLIENT_ID)
              .setClientSecret(CLIENT_SECRET)
              .setRedirectUri(REDIRECT_URI)
              .build();

      // Obtenemos un token de acceso a la API de Spotify
      String accessToken = spotifyApi.clientCredentials().build().execute().getAccessToken();
      spotifyApi.setAccessToken(accessToken);

      // Realizamos la búsqueda de canciones en Spotify
      SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(QUERY)
              .market("ES")
              .limit(1)
              .offset(0)
              .build();

      Paging<Track> tracks = searchTracksRequest.execute();

      // Mostramos el resultado de la búsqueda al usuario
      if (tracks.getTotal() > 0) {
        Track track = tracks.getItems()[0];
        System.out.println("Esta es la canción recomendada para ti: " + track.getName() + " - " + track.getArtists()[0].getName());
        System.out.println("Escúchala en Spotify: " + track.getExternalUrls().get("spotify"));
      } else {
        System.out.println("No se encontró ninguna canción para mejorar tu estado de ánimo");
      }

    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Ocurrió un error al conectar con la API de Spotify: " + e.getMessage());
    }
  }
}