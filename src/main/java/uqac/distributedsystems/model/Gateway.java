package uqac.distributedsystems.model;

/**
 * The type Gateway.
 */
public class Gateway {
    private String name;
    private String technology;
    private Coordinate[] coords = new Coordinate[2];

    /**
     * Instantiates a new Gateway.
     *
     * @param name       the name
     * @param technology the technology
     * @param coords     the coords
     */
    public Gateway(String name, String technology, Coordinate[] coords) {
        this.name = name;
        this.technology = technology;
        this.coords[0] = coords[0];
        this.coords[1] = coords[1];
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets technology.
     *
     * @return the technology
     */
    public String getTechnology() {
        return technology;
    }

    /**
     * Sets technology.
     *
     * @param technology the technology
     */
    public void setTechnology(String technology) {
        this.technology = technology;
    }

    /**
     * Get coords coordinate [ ].
     *
     * @return the coordinate [ ]
     */
    public Coordinate[] getCoords() {
        return coords;
    }

    /**
     * Sets coords.
     *
     * @param coords the coords
     */
    public void setCoords(Coordinate[] coords) {
        this.coords = coords;
    }
}
