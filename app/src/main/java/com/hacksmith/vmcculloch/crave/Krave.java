package com.hacksmith.vmcculloch.crave;

//Item Class

import java.io.*;
import java.util.*;

public class Krave{
    class Item {
        ArrayList<String> allergyList;
        ArrayList<String> tags;

        String name;

        boolean ageLimit;
        boolean vegan;
        boolean glutenFree;

        Item(String n, int ageBit, int veganBit, int glutenBit){
            name = n;
            ageLimit = (ageBit == 0);
            vegan = (veganBit == 0);
            glutenFree = (glutenBit == 0);

            allergyList = new ArrayList<String>();
            tags = new ArrayList<String>();
        }

        boolean isAgeLimit(){
            return ageLimit;
        }

        boolean isVegan(){
            return vegan;
        }

        boolean isGlutenFree(){
            return glutenFree;
        }

        String getName(){
            return name;
        }
    }

    class Food extends Item{
        ArrayList<Food> relatedList;
        ArrayList<Drink> drinkList;
        ArrayList<String> tagList;

        Food(String n, int ageBit, int veganBit, int glutenBit){
            super(n, ageBit, veganBit, glutenBit);
            relatedList = new ArrayList<Food>();
            drinkList = new ArrayList<Drink>();
        }

        void addRelatedFood(ArrayList<Food> suggestList){
            for(Food f : suggestList){
                //Java's version of for in loops
                relatedList.add(f);
            }
        }

        void drinkList(ArrayList<Drink> suggestList){
            for(Drink d : suggestList){
                relatedList.add(d);
            }
        }

        void setTag(ArrayList<String>tags){
            tagList = tags;
        }

        ArrayList<String> getTag(){
            return tagList;
        }
    }

    class Drink extends Food{
        Drink(String n, int ageBit, int veganBit, int glutenBit){
            super(n, ageBit, veganBit, glutenBit);
            relatedList = new ArrayList<Food>();
            drinkList = new ArrayList<Drink>();
        }
    }

    class Node
    {
        int guide;
        Food value;
    }

    class InternalNode extends Node
    {
        Node child0, child1, child2;
        // child0 and child1 are always non-null
        // child2 is null iff node has only 2 children
        void updateGuide()
        {
            if (child2 == null)
            {
                guide = child1.guide;
            }
            else
            {
                guide = child2.guide;
            }
        }
    }

    class LeafNode extends Node
    {
        // guide points to the key

    }

    class TwoThreeTree
    {
        Node root;
        int height;


        ArrayList<String> results = new ArrayList<String>();

        void printGivenLevel(Node root, int level)
        {
            if (root == null)
                return;
            if (level == 0)
                results.add(root.value.getName());
            else if (level > 0)
            {
                InternalNode c = (InternalNode) root;
                printGivenLevel(c.child2, level-1);
                printGivenLevel(c.child1, level-1);
                printGivenLevel(c.child0, level-1);

            }
        }

        ArrayList<String> getResult()
        {
            printGivenLevel(this.root, this.height);
            return results;
        }


        Node[] insert(int x, Food v, Node r, int h)
        {
            Node[] ret = new Node[2];
            if (r == null)  //base case no node existing
            {
                LeafNode l = new LeafNode();
                l.guide = x;
                l.value = v;
                root = l;
                height = 0;
                return ret;
            }
            if (h == 0) //base case 1 or 2 nodes existing
            {
                LeafNode l = new LeafNode();
                l.guide = x;
                l.value = v;
                InternalNode p = new InternalNode();
                if (x <= root.guide)
                {
                    p.child0 = l;
                    p.child1 = root;
                }
                else
                {
                    p.child0 = root;
                    p.child1 = l;
                }
                p.updateGuide();
                root = p;
                height++;
                return ret;
            }

            if (h == 1)
            {
                InternalNode p = (InternalNode) r;
                if (p.child2 != null)
                {
                    InternalNode n1 = new InternalNode();
                    InternalNode n2 = new InternalNode();
                    LeafNode l = new LeafNode();
                    l.guide = x;
                    l.value = v;

                    if (x <= p.child0.guide)
                    {
                        n1.child0 = l;
                        n1.child1 = p.child0;
                        n2.child0 = p.child1;
                        n2.child1 = p.child2;
                    }
                    else if (x <= p.child1.guide)
                    {
                        n1.child0 = p.child0;
                        n1.child1 = l;
                        n2.child0 = p.child1;
                        n2.child1 = p.child2;
                    }
                    else if (x <= p.child2.guide)
                    {
                        n1.child0 = p.child0;
                        n1.child1 = p.child1;
                        n2.child0 = l;
                        n2.child1 = p.child2;
                    }
                    else
                    {
                        n1.child0 = p.child0;
                        n1.child1 = p.child1;
                        n2.child0 = p.child2;
                        n2.child1 = l;
                    }

                    n1.updateGuide();
                    n2.updateGuide();

                    if (p == root)
                    {
                        InternalNode newR = new InternalNode();
                        newR.child0 = n1;
                        newR.child1 = n2;
                        newR.updateGuide();
                        root = newR;
                        height++;
                    }

                    ret[0] = n1;
                    ret[1] = n2;
                    return ret;
                }
                else
                {
                    LeafNode l = new LeafNode();
                    l.guide = x;
                    l.value = v;
                    InternalNode newP = new InternalNode();
                    if (l.guide <= p.child0.guide)
                    {
                        newP.child0 = l;
                        newP.child1 = p.child0;
                        newP.child2 = p.child1;

                    }
                    else if (l.guide <= p.child1.guide)
                    {
                        newP.child0 = p.child0;
                        newP.child1 = l;
                        newP.child2 = p.child1;
                    }
                    else {
                        newP.child0 = p.child0;
                        newP.child1 = p.child1;
                        newP.child2 = l;
                    }
                    newP.updateGuide();

                    if (p == root)
                    {
                        root = newP;
                    }
                    ret[0] = newP;
                    ret[1] = null;
                    return ret;
                }
            }
            else
            {
                InternalNode p = (InternalNode) r;
                Node[] returned = new Node[2];
                if (x <= p.child0.guide)
                {
                    returned = insert(x, v, p.child0, h - 1);
                }
                else if (x <= p.child1.guide || p.child2 == null)
                {
                    returned = insert(x, v, p.child1, h - 1);
                }
                else
                {
                    returned = insert(x, v, p.child2, h - 1);
                }
                if (returned[1] != null)
                {
                    Node[] ret2 = new Node[2];
                    if (p.child2 != null)
                    {
                        InternalNode n1 = new InternalNode();
                        InternalNode n2 = new InternalNode();

                        if (returned[1].guide <= p.child0.guide)
                        {
                            n1.child0 = returned[0];
                            n1.child1 = returned[1];
                            n2.child0 = p.child1;
                            n2.child1 = p.child2;
                        }
                        else if (returned[1].guide <= p.child1.guide)
                        {
                            n1.child0 = p.child0;
                            n1.child1 = returned[0];
                            n2.child0 = returned[1];
                            n2.child1 = p.child2;
                        }
                        else
                        {
                            n1.child0 = p.child0;
                            n1.child1 = p.child1;
                            n2.child0 = returned[0];
                            n2.child1 = returned[1];
                        }

                        n1.updateGuide();
                        n2.updateGuide();


                        if (p == root)
                        {
                            InternalNode newP = new InternalNode();
                            newP.child0 = n1;
                            newP.child1 = n2;
                            newP.updateGuide();
                            root = newP;
                            height++;
                        }
                        ret2[0] = n1;
                        ret2[1] = n2;
                        return ret2;
                    }
                    else
                    {
                        InternalNode newP = new InternalNode();
                        if (returned[1].guide <= p.child0.guide)
                        {
                            newP.child2 = p.child1;
                            newP.child0 = returned[0];
                            newP.child1 = returned[1];
                        }
                        else
                        {
                            newP.child0 = p.child0;
                            newP.child1 = returned[0];
                            newP.child2 = returned[1];
                        }
                        newP.updateGuide();
                        if (p == root)
                        {
                            root = newP;
                        }
                        ret2[0] = newP;
                        ret2[1] = null;
                        return ret2;
                    }
                }
                else
                {
                    InternalNode newP = new InternalNode();
                    if (h >= 2)
                    {
                        if (p.child2 != null)
                        {
                            if (returned[0].guide <= p.child0.guide)
                            {
                                newP.child0 = returned[0];
                                newP.child1 = p.child1;
                                newP.child2 = p.child2;
                            }
                            else if (returned[0].guide <= p.child1.guide)
                            {
                                newP.child0 = p.child0;
                                newP.child1 = returned[0];
                                newP.child2 = p.child2;
                            }
                            else
                            {
                                newP.child0 = p.child0;
                                newP.child1 = p.child1;
                                newP.child2 = returned[0];
                            }

                        }
                        else
                        {
                            if (returned[0].guide <= p.child0.guide)
                            {
                                newP.child0 = returned[0];
                                newP.child1 = p.child1;
                            }
                            else
                            {
                                newP.child0 = p.child0;
                                newP.child1 = returned[0];
                            }
                        }
                        newP.updateGuide();

                    }
                    if (p == root)
                    {
                        root = newP;
                    }

                    ret[0] = newP;
                    ret[1] = null;
                    return ret;
                }
            }
        }
    }

    //insert by calling

    //after all the insert process finishes, call
    ArrayList<String> resultString(String input){
        ArrayList<String> userKeyList = new ArrayList<String>();
        String[] str = input.split(", ");
        for(String s : str){
            userKeyList.add(s);
        }

        TwoThreeTree scoreTree = new TwoThreeTree();
        for(int i = 0; i < foodList.size(); i++)
        {
            Food f = foodList.get(i);
            int score = getScore(userKeyList, f);
            scoreTree.insert(score, f, scoreTree.root, scoreTree.height);
        }

        ArrayList<String> results = scoreTree.getResult();
        return results;
    }

    int getScore(ArrayList<String> keyWordList, Food f){
        int score = 0;
        for(int i = 0; i < keyWordList.size(); i++)
        {
            String curKey = keyWordList.get(i);
            for(int j = 0; j < f.getTag().size(); j++)
            {
                if(curKey.equalsIgnoreCase(f.getTag().get(j)))
                {
                    score++;
                }
            }
        }
        return score;
    }

    void addTags(String name, int numTags, String tags){
        for(int i = 0; i < foodList.size(); i++){
            if(foodList.get(i).getName().equalsIgnoreCase(name)){
                ArrayList<String> tagList = new ArrayList<String>();
                String[] splitTags = tags.split("\\s+");
                for(String s : splitTags){
                    tagList.add(s);
                }
                foodList.get(i).setTag(tagList);
            }
        }
    }

    boolean veganToggle;
    boolean ageToggle;
    boolean glutenToggle;

    ArrayList<Food> foodList = new ArrayList<Food>();

    File foodFile = new File("foodList.txt");
    File drinkFile = new File("drinkList.txt");

    void addItems(){
        File food = new File("foodList.txt");
        try (Scanner in = new Scanner(food)){
            String temp = "";
            int counter = 0;
            while(in.hasNext()){
                String s = in.next();
                if(s.substring(s.length()-1).equals(",")){
                    counter += 1;
                    temp += s;
                    temp = temp.substring(0, temp.length()-1);
                    if(counter % 4 == 0){
                        //					System.out.println(temp);
                        String[] a = temp.split(" ");
                        int len = a.length;
                        String tem = "";
                        for(int i = 0; i < a.length-3; i++){
                            tem += a[i];
                        }
                        Food f = new Food(tem, Integer.parseInt(a[len-3]), Integer.parseInt(a[len-2]), Integer.parseInt(a[len-1]));
                        foodList.add(f);
                        temp = "";
                    }
                    else{
                        temp +=  " ";
                    }
                }
                else if(counter % 4 == 3){
                    temp += s;
                    String[] a = temp.split(" ");
                    int len = a.length;
                    String tem = "";
                    for(int i = 0; i < a.length-3; i++){
                        tem += a[i];
                    }
                    Food f = new Food(tem, Integer.parseInt(a[len-3]), Integer.parseInt(a[len-2]), Integer.parseInt(a[len-1]));
                    foodList.add(f);
                    temp = "";
                    //				System.out.println(temp);
                }
                else{
                    temp += s + " ";
                }
            }
        }
        catch(Exception e){
            System.out.println("error");
        }

        // Drink List Scanner implementation
    }
}
