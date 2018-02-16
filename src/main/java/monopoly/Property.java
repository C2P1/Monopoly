package monopoly;

public class Property{ 
    private String Name;
    private double price;
    private double mortgagePrice;
    private double housePrice;
    private double hotelPrice;
    private boolean isMortgaged;
    private boolean isOwned;
    private double rent;
    private Player player;
    private int numHouses = 0;
    private int numHotels = 0;
    private int setNum;//the row location in the properties arrayList; useful when determining if all properties in the set are owned
    private int numinset;
    public Property(String n, int p, int r, int mPrice, int hPrice, int setNum, int inset) {
        Name=n;
        price=p;
        rent=r;
        numHouses=0;
        numHotels=0;
        housePrice = hPrice;
        hotelPrice = hPrice;
        mortgagePrice = mPrice;
        this.setNum = setNum; //brown=0, lightBlue=1, purple=2, ...
        isOwned=false;
        isMortgaged=false;
        setPlayer(null);
        numinset=inset;
    }
    
    public boolean buy(Player buyer) {
    	if (!isOwned) {
			isOwned = true;
			setPlayer(buyer);
			return true;
		} else {
			return false;
		}
    }
    
    public boolean sell() {
    	if (isOwned) {
			if(numHouses == 0 && numHotels == 0) {
				setPlayer(null);
				isOwned = false;
				return true;
			} else {
				System.out.println("You cannot sell a property with Houses or Hotels on it");
				return false;
			}
		}
		return false;
    }
    
    public boolean mortgage() {
    	if (isOwned) {
			if (!isMortgaged) {
				isMortgaged = true; 
				return true;
			} 
		} 
		return false;
    }
    
    public boolean unmortgage() {
    	if (isOwned) {
			if (isMortgaged) {
				isMortgaged = false;
				return true;
			} 
		}
		return false;
    }
    
    public double getPrice() {
        return price;
    }
    
    public double getHousePrice() {
        return housePrice;
    }
    
    public double getHotelPrice() {
        return hotelPrice;
    }
    
    public double getMortgagePrice() {
        return mortgagePrice;
    }
    
    public boolean buyHouse() {
    	if(numHouses < 4) {
			numHouses++;
			return true;
		} else {
			System.out.println("You cannot buy houses on this property");
			return false;
		}
    }
    
    public boolean buyHotel() {
    	if(numHouses == 4) {
			if(numHotels < 1) {
				numHotels++;
				numHouses = 0;
				return true;
			} else {
				System.out.println("You already have a hotel on this property");
				return false;
			}
		} else {
			System.out.println("You need to buy more houses on this property before you can do that");
			return false;
		}
    }
    
    public boolean sellHouse() {
    	if(numHouses > 0) {
			numHouses--;
			return true;
		} else {
			System.out.println("You don't have any houses on this property");
			return false;
		}
    }
    
    public boolean sellHotel() {
    	if(numHotels > 0) {
			numHotels--;
			numHouses = 0;
			return true;
		} else {
			System.out.println("You don't have any hotels on this property");
			return false;
		}
    }
    
    public double getRent() {
    	return (int) (rent + ((price*0.5)*numHouses) + ((price*4)*numHotels));
    }
    
    public boolean getIsMortgaged() {
		return isMortgaged;
	}

	public int getNumHouses() {
		return numHouses;
	}

	public int getNumHotels() {
		return numHotels;
	}

	public double getHouseCost() {
		return housePrice;
	}

	public double getHotelCost() {
		return hotelPrice;
	}

	public boolean getIsOwned() {
		return isOwned;
	}
	
	public Player getOwner() {
		return getPlayer();
	}
    
	//return the name of the property
	public String getName() {
		return Name;
	}
	
	//return the setNum of the property
	public int getSetNum() {
		return setNum;
	}
	
	public int getNumInSet() {
		return numinset;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}

