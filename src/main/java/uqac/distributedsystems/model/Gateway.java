package uqac.distributedsystems.model;

import java.awt.*;

/**
 * The type Gateway.
 */
public class Gateway {
    private String name;
    private String technology;
    private Coordinate[] coords = new Coordinate[2];
    private Coordinate currentCoords;
    private boolean b = false; //Boolean pour savoir ou on doit se diriger
    private boolean hasMessage = false; //Boolean pour savoir si la passerelle a déjà récupéré le message

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
        this.currentCoords = coords[0];
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

    /**
     * Get currentCoords
     *
     * @return the current coords
     */
    public Coordinate getCurrentCoords() {
        return currentCoords;
    }

    /**
     * Set currentsCoords
     *
     * @param currentCoords the current coords
     */
    public void setCurrentCoords(Coordinate currentCoords) {
        this.currentCoords = currentCoords;
    }

    /**
     * Pour changer la variable hsMessage
     */
    public void setHasMessage(){
        hasMessage = !hasMessage;
    }

    /**
     * Pour savoir si la passerelle a le message ou pas
     *
     * @return hasMessage
     */
    public boolean isHasMessage() {
        return hasMessage;
    }

    /**
     * Affiche le composant dans la fenetre
     * @param g (Graphics)
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(this.getCurrentCoords().getX(), this.getCurrentCoords().getY(), 8, 8);
        g.drawString(this.getName(), this.getCurrentCoords().getX(), this.getCurrentCoords().getY());
    }

    /**
     * Pour savoir où aller
     *
     * @return coord
     */
    public Coordinate getDestination(){
        if(b){
            return coords[1];
        }
        else{
            return coords[0];
        }
    }

    /**
     * Pour changer la destination
     */
    public void changeDestination(){
        b = !b;
    }
}
