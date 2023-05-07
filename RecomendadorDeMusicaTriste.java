import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

public class RecomendadorDeMusicaTriste {

  private static final String CLIENT_ID = "TU_CLIENT_ID";
  private static final String CLIENT_SECRET = "TU_CLIENT_SECRET";
  private static final String REDIRECT_URI = "TU_REDIRECT_URI";
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

      // Buscamos una canción en Spotify
      SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(QUERY).limit(1).build();
      Paging<Track> trackPaging = searchTracksRequest.execute();
      Track[] tracks = trackPaging.getItems();

      // Mostramos el link de la canción encontrada
      if (tracks.length > 0) {
        System.out.println("¡Esta canción te hará sentir mejor! " + tracks[0].getExternalUrls().get("spotify"));
      } else {
        System.out.println("No se encontró ninguna canción");
      }
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error al buscar la canción: " + e.getMessage());
    }
  }
}