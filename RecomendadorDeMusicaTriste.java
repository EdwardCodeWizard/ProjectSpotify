import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

public class RecomendadorDeMusicaTriste {

  private static final String CLIENT_ID = "CLIENT_ID";
  private static final String CLIENT_SECRET = "CLIENT_SECRET";
  private static final String REDIRECT_URI = "REDIRECT_URI";
  private static final String QUERY = "sad";

  public void recomendarCancion() {
    try {
      // Conectamos con la API de Spotify
      SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setClientId(CLIENT_ID)
          .setClientSecret(CLIENT_SECRET)
          .setRedirectUri(REDIRECT_URI)
          .build();
      spotifyApi.clientCredentials().build().execute();
      String accessToken = spotifyApi.getAccessToken();

      // Realizamos la búsqueda de una canción triste en Spotify
      SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(QUERY)
          .market("ES")
          .limit(1)
          .build();
      Paging<Track> paging = searchTracksRequest.execute();
      Track[] tracks = paging.getItems();
      if (tracks.length > 0) {
        System.out.println("Te recomendamos escuchar: " + tracks[0].getName() + " de " + tracks[0].getArtists()[0].getName());
        System.out.println("Escúchala en Spotify: " + tracks[0].getExternalUrls().get("spotify"));
      } else {
        System.out.println("No se encontraron canciones tristes en Spotify");
      }

    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error al conectar con la API de Spotify");
    }
  }
}