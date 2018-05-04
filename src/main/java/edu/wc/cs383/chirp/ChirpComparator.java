package edu.wc.cs383.chirp;

import java.util.*;

public class ChirpComparator implements Comparator<Chirp>{

	@Override
	public int compare(Chirp c1, Chirp c2) {
		return c2.getDate().compareTo(c1.getDate());
	}

}
