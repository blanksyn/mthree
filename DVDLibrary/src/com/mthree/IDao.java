package com.mthree;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Repository;

@Repository(value = "inMemory")
public class IDao {
	private List<DVD> dvds = new ArrayList<DVD>();
	
	private void listToTxt(List<DVD> dvds) {
		FileOutputStream fos= null;
        BufferedOutputStream bos = null;

        try{
            fos = new FileOutputStream("./../../workspace/DVDLibrary/src/com/mthree/DVDLib.txt");
            bos = new BufferedOutputStream(fos);

            PrintStream p = new PrintStream(bos);
            
            for(int i =0; i<dvds.size();i++) {
            	String data = dvds.get(i).getId()+"::"+ dvds.get(i).getTitle()+"::"+ dvds.get(i).getReleaseDate()
            			+"::"+dvds.get(i).getMpaaRating()+"::"+ dvds.get(i).getDirectorName()+"::"+dvds.get(i).getStudio()+"::"+dvds.get(i).getNote();
            	p.println(data);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	private void fillList() {
		if(dvds.size()>0) {
			dvds.clear();
		}
		Scanner myReader = null;
        try {
            File myObj = new File("./../../workspace/DVDLibrary/src/com/mthree/DVDLib.txt");
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                System.out.println(data);
                String[] dataDetail = data.split("::");
                dvds.add(new DVD(Integer.parseInt(dataDetail[0]),dataDetail[1]
                		,dataDetail[2],Double.parseDouble(dataDetail[3]),dataDetail[4]
                				,dataDetail[5],dataDetail[6]));
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not present");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            myReader.close();
        }
	}
	
	private void addToTxt(DVD dvd) {
		FileOutputStream fos= null;
        BufferedOutputStream bos = null;

        String data = dvd.getId()+"::"+ dvd.getTitle()+"::"+ dvd.getReleaseDate()
        				+"::"+dvd.getMpaaRating()+"::"+ dvd.getDirectorName()+"::"+dvd.getStudio()+"::"+dvd.getNote();
        try{
            fos = new FileOutputStream("./../../workspace/DVDLibrary/src/com/mthree/DVDLib.txt",true);
            bos = new BufferedOutputStream(fos);

            PrintStream p = new PrintStream(bos);
            p.println(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public List<DVD> findAll(){
//		System.out.println("Inside findAll() of class UserInMemoryDao");
		fillList();
		return dvds;
	}
	
	public Optional<DVD> findById(int id){
//		System.out.println("Inside findById(int id) of class UserInMemoryDao");
		DVD user=dvds.stream().filter(a -> a.getId() == id).findAny().orElse(null);
		System.out.println("User: " + user);
	     Optional<DVD> opt = Optional.ofNullable(user);
	        return opt;

	}

	public DVD save(DVD dvd) {
//		System.out.println("Inside save() of UserInMemoryDao");
		addToTxt(dvd);
		System.out.println("Saved to txt file");
//		dvds.add(dvd);
		return dvd;
	}
	
	public List<DVD> deleteById(int id) {
//		System.out.println("Inside deleteById() of UserInMemoryDao");
		Iterator<DVD> iter = dvds.iterator();
		while(iter.hasNext()) {
			DVD dvd = iter.next();
			if (dvd.getId() == id) {
				iter.remove();
//				System.out.println("id removed from iter: " + id);
				System.out.println(dvds);
				
			}
		}
		for(int i = 0; i<dvds.size();i++) {
			dvds.get(i).setId(i+1);
		}
		System.out.println(dvds);
		listToTxt(dvds);
//		System.out.println("Text file updated");
		fillList();
//		System.out.println("New list filled");
		return dvds;
		
	}

	public List<Optional<DVD>> findByTitle(String title) {
//		System.out.println("Inside findByTitle(String title) of class UserInMemoryDao");
		List<Optional<DVD>> list = new ArrayList<Optional<DVD>>();
		for(DVD u :dvds) {
			if(u.getTitle().equals(title)) {
				list.add(Optional.of(u));
			}
		}
		return list;
	}

	public DVD dispEdit(int id) {
//		System.out.println("Inside dispEdit() of UserInMemoryDao");
		Iterator<DVD> iter = dvds.iterator();
		while(iter.hasNext()) {
			DVD dvd = iter.next();
			if (dvd.getId() == id) {
				
				return dvd;
			}
		}
		return null;
	}

	public DVD edit(DVD dvd) {
//		System.out.println("Inside edit() of UserInMemoryDao");
		for(int i=0;i<dvds.size();i++) {
			if(dvds.get(i).getId()==dvd.getId()) {
				dvds.get(i).setDirectorName(dvd.getDirectorName());
				dvds.get(i).setMpaaRating(dvd.getMpaaRating());
				dvds.get(i).setNote(dvd.getNote());
				dvds.get(i).setReleaseDate(dvd.getReleaseDate());
				dvds.get(i).setStudio(dvd.getStudio());
				dvds.get(i).setTitle(dvd.getTitle());
			}
		}
		
		listToTxt(dvds);
		fillList();
		return dvd;
	}

}
