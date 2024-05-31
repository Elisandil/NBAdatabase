package datosPrueba;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipo;
import modelo.EstadisticasEquipoPorTemporada;
import modelo.Jugador;
import modelo.Partido;
import modelo.Temporada;

public class Prueba {

    public static List<Equipo> generarEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        
        equipos.add(new Equipo(null, "Los Angeles Lakers", "Los Angeles", "Pacífico", "Oeste", "Staples Center", "Frank Vogel"));
        equipos.add(new Equipo(null, "Golden State Warriors", "San Francisco", "Pacífico", "Oeste", "Chase Center", "Steve Kerr"));
        equipos.add(new Equipo(null, "Boston Celtics", "Boston", "Atlántico", "Este", "TD Garden", "Brad Stevens"));
        equipos.add(new Equipo(null, "Chicago Bulls", "Chicago", "Central", "Este", "United Center", "Billy Donovan"));
        equipos.add(new Equipo(null, "Miami Heat", "Miami", "Sureste", "Este", "AmericanAirlines Arena", "Erik Spoelstra"));
        equipos.add(new Equipo(null, "New York Knicks", "New York", "Atlántico", "Este", "Madison Square Garden", "Tom Thibodeau"));
        equipos.add(new Equipo(null, "Brooklyn Nets", "Brooklyn", "Atlántico", "Este", "Barclays Center", "Steve Nash"));
        equipos.add(new Equipo(null, "Philadelphia 76ers", "Philadelphia", "Atlántico", "Este", "Wells Fargo Center", "Doc Rivers"));
        equipos.add(new Equipo(null, "San Antonio Spurs", "San Antonio", "Suroeste", "Oeste", "AT&T Center", "Gregg Popovich"));
        equipos.add(new Equipo(null, "Dallas Mavericks", "Dallas", "Suroeste", "Oeste", "American Airlines Center", "Rick Carlisle"));
        
        return equipos;
    }

    public static List<Temporada> generarTemporadas() {
        List<Temporada> temporadas = new ArrayList<>();

        temporadas.add(new Temporada(null, 2020, "Regular", "Finalizada", "Temporada regular 2019-2020"));
        temporadas.add(new Temporada(null, 2020, "Playoffs", "Finalizada", "Playoffs 2020"));
        temporadas.add(new Temporada(null, 2021, "Regular", "En curso", "Temporada regular 2020-2021"));
        temporadas.add(new Temporada(null, 2021, "Playoffs", "No iniciada", "Playoffs 2021"));
        temporadas.add(new Temporada(null, 2022, "Regular", "No iniciada", "Temporada regular 2021-2022"));
        
        return temporadas;
    }

    public static List<Jugador> generarJugadores(List<Equipo> equipos) {
        List<Jugador> jugadores = new ArrayList<>();

        jugadores.add(new Jugador(null, "LeBron", "James", "Alero", 2.06, 113.4,
                LocalDate.of(1984, 12, 30), 2));
        jugadores.add(new Jugador(null, "Stephen", "Curry", "Base", 1.91, 86.2,
                LocalDate.of(1988, 3, 14), 3));
        jugadores.add(new Jugador(null, "Kevin", "Durant", "Ala-pivot", 2.08, 108.9,
                LocalDate.of(1988, 9, 29), 7));
        jugadores.add(new Jugador(null, "Giannis", "Antetokounmpo", "Alero", 2.11, 109.8,
                LocalDate.of(1994, 12, 6), 4));
        jugadores.add(new Jugador(null, "Luka", "Dončić", "Base", 2.01, 104.3,
                LocalDate.of(1999, 2, 28), 10));
        jugadores.add(new Jugador(null, "Kawhi", "Leonard", "Escolta", 2.01, 104.3,
                LocalDate.of(1991, 6, 29), 5));
        jugadores.add(new Jugador(null, "Nikola", "Jokić", "Pivot", 2.11, 113.4,
                LocalDate.of(1995, 2, 19), 9));
        jugadores.add(new Jugador(null, "James", "Harden", "Escolta", 1.96, 99.8,
                LocalDate.of(1989, 8, 26), 7));
        jugadores.add(new Jugador(null, "Joel", "Embiid", "Pivot", 2.13, 127.0,
                LocalDate.of(1994, 3, 16), 8));
        jugadores.add(new Jugador(null, "Kyrie", "Irving", "Base", 1.91, 88.5,
                LocalDate.of(1992, 3, 23), 6));
        jugadores.add(new Jugador(null, "Anthony", "Davis", "Ala-pivot", 2.08, 113.4,
                LocalDate.of(1993, 3, 11), 2));
        jugadores.add(new Jugador(null, "Klay", "Thompson", "Escolta", 2.01, 96.2,
                LocalDate.of(1990, 2, 8), 3)); 
        jugadores.add(new Jugador(null, "Jayson", "Tatum", "Alero", 2.03, 93.0,
                LocalDate.of(1998, 3, 3), 6)); 
        jugadores.add(new Jugador(null, "Jimmy", "Butler", "Escolta", 2.01, 102.1,
                LocalDate.of(1989, 9, 14), 4));
        jugadores.add(new Jugador(null, "Zion", "Williamson", "Alero", 1.98, 129.3,
                LocalDate.of(2000, 7, 6), 11));
        jugadores.add(new Jugador(null, "Devin", "Booker", "Escolta", 1.96, 95.3,
                LocalDate.of(1996, 10, 30), 12));
        jugadores.add(new Jugador(null, "Trae", "Young", "Base", 1.85, 82.1,
                LocalDate.of(1998, 9, 19), 10)); 
        jugadores.add(new Jugador(null, "Russell", "Westbrook", "Base", 1.91, 90.7,
                LocalDate.of(1988, 11, 12), 2)); 
        jugadores.add(new Jugador(null, "Ja", "Morant", "Base", 1.91, 82.1,
                LocalDate.of(1999, 8, 10), 1)); 
        jugadores.add(new Jugador(null, "Damian", "Lillard", "Base", 1.88, 88.5,
                LocalDate.of(1990, 7, 15), 5)); 
        jugadores.add(new Jugador(null, "Chris", "Paul", "Base", 1.83, 79.4,
                LocalDate.of(1985, 5, 6), 8)); 
        jugadores.add(new Jugador(null, "Brandon", "Ingram", "Alero", 2.03, 86.2,
                LocalDate.of(1997, 9, 2), 11));
        jugadores.add(new Jugador(null, "Karl-Anthony", "Towns", "Pivot", 2.13, 112.5,
                LocalDate.of(1995, 11, 15), 1));
        jugadores.add(new Jugador(null, "Jamal", " Murray", "Escolta", 1.93, 83.9,
                LocalDate.of(1997, 2, 23), 9)); 
        jugadores.add(new Jugador(null, "Ben", "Simmons", "Base", 2.08, 104.3,
                LocalDate.of(1996, 7, 20), 8)); 
        jugadores.add(new Jugador(null, "Jaylen", "Brown", "Escolta", 1.98, 100.7,
                LocalDate.of(1996, 10, 24), 6)); 
        jugadores.add(new Jugador(null, "Deandre", "Ayton", "Pivot", 2.11, 110.2,
                LocalDate.of(1998, 7, 23), 12)); 
        jugadores.add(new Jugador(null, "John", "Wall", "Base", 1.93, 95.3,
                LocalDate.of(1990, 9, 6), 10)); 
        jugadores.add(new Jugador(null, "Kristaps", "Porziņģis", "Pivot", 2.21, 109.8,
                LocalDate.of(1995, 8, 2), 10)); 
        jugadores.add(new Jugador(null, "Domantas", "Sabonis", "Pivot", 2.11, 106.1,
                LocalDate.of(1996, 5, 3), 1)); 
        jugadores.add(new Jugador(null, "De'Aaron", "Fox", "Base", 1.93, 86.2,
                LocalDate.of(1997, 12, 20), 11));
        jugadores.add(new Jugador(null, "Myles", "Turner", "Pivot", 2.11, 113.4,
                LocalDate.of(1996, 3, 24), 12)); 
        
        return jugadores;
    }
    
    public static List<Partido> generarPartidos(List<Equipo> equipos, List<Temporada> temporadas) {
        List<Partido> partidos = new ArrayList<>();
        
        partidos.add(new Partido(null, LocalDate.of(2020, 12, 22), 
                obtenerEquipoPorNombre(equipos, "Los Angeles Lakers"), 
                obtenerEquipoPorNombre(equipos, "Dallas Mavericks"), 
                120, 110, 1));
        
        partidos.add(new Partido(null, LocalDate.of(2020, 12, 25), 
                obtenerEquipoPorNombre(equipos, "Brooklyn Nets"), 
                obtenerEquipoPorNombre(equipos, "Golden State Warriors"), 
                125, 99, 1));
        
        partidos.add(new Partido(null, LocalDate.of(2021, 3, 18), 
                obtenerEquipoPorNombre(equipos, "Boston Celtics"), 
                obtenerEquipoPorNombre(equipos, "Miami Heat"), 
                105, 101, 3));
        
        partidos.add(new Partido(null, LocalDate.of(2021, 5, 30), 
                obtenerEquipoPorNombre(equipos, "Philadelphia 76ers"), 
                obtenerEquipoPorNombre(equipos, "New York Knicks"), 
                115, 112,2));
        
        partidos.add(new Partido(null, LocalDate.of(2022, 4, 10), 
                obtenerEquipoPorNombre(equipos, "San Antonio Spurs"), 
                obtenerEquipoPorNombre(equipos, "Chicago Bulls"), 
                102, 98, 3));
        
        return partidos;
    }
    
    public static List<EstadisticasEquipoPorTemporada> generarEstadisticasEquipos() {
        List<EstadisticasEquipoPorTemporada> estadisticasEquipos = new ArrayList<>();

        estadisticasEquipos.add(new EstadisticasEquipoPorTemporada(null, 7500, 5200, 45, 15, "45-15", 2, 1));
        estadisticasEquipos.add(new EstadisticasEquipoPorTemporada(null, 8000, 5500, 40, 20, "40-20", 3, 1));
        estadisticasEquipos.add(new EstadisticasEquipoPorTemporada(null, 8000, 9000, 25, 35, "25-35", 4, 1));
        estadisticasEquipos.add(new EstadisticasEquipoPorTemporada(null, 7000, 5400, 50, 10, "50-10", 5, 1));
        estadisticasEquipos.add(new EstadisticasEquipoPorTemporada(null, 8500, 5800, 38, 22, "38-22", 6, 2));
        estadisticasEquipos.add(new EstadisticasEquipoPorTemporada(null, 9500, 6300, 33, 27, "33-27", 7, 2));
        estadisticasEquipos.add(new EstadisticasEquipoPorTemporada(null, 7200, 5600, 48, 12, "48-12", 8, 1));
        estadisticasEquipos.add(new EstadisticasEquipoPorTemporada(null, 7800, 5700, 42, 18, "42-18", 9, 2));

        return estadisticasEquipos;
    }    
    
    private static Equipo obtenerEquipoPorNombre(List<Equipo> equipos, String nombre) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equals(nombre)) {
                return equipo;
            }
        }
        return null;
    }    
}
