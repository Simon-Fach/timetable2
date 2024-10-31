package org.example;


import Klassen.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
  public static void main (String[] args) {
    ArrayList<ArrayList<FinalBooking>> bookings = new ArrayList<>();
    bookings.add(new ArrayList<FinalBooking>()); // Mon
    bookings.add(new ArrayList<FinalBooking>()); // Tue
    bookings.add(new ArrayList<FinalBooking>()); // Wed
    bookings.add(new ArrayList<FinalBooking>()); // Thu
    bookings.add(new ArrayList<FinalBooking>()); // Fri

    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(University.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      Path currentRelativePath = Paths.get("");
      String path = currentRelativePath.toAbsolutePath() + "\\src\\main\\java\\org\\example\\timetable.xml";
      JAXBElement<University> jaxbElement = (JAXBElement<University>) jaxbUnmarshaller.unmarshal(new File(path));
      University university = jaxbElement.getValue();
      

      for (Lecture lecture : university.getLectures().getLecture()) {
        final String course = lecture.getName();
        final String id = lecture.getId();

        for (Booking booking : lecture.getRoombookings().getBooking()) {
          final FinalBooking finalBooking = new FinalBooking(id, course, booking);
          bookings.get(weekday(finalBooking.getWeekday())).add(finalBooking);
        }
      }

      Map<String, ArrayList<String>> curricula = new HashMap<>();

      for (Curriculum curriculum : university.getCurricula().getCurriculum()) {
        final String name = curriculum.getName();

        for (int i = 0; i < curriculum.getLecture().size(); i++) {
          final Lecture lecture = (Lecture) curriculum.getLecture().get(i).getValue();
          final String key = lecture.getId();

          if (curricula.containsKey(key)) {
            curricula.get(key).add(name);
          } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(name);
            curricula.put(key, list);
          }
        }
      }

      String ruleA = "Violations of Rule 1: \n" + "========================================= \n";
      String ruleB = "Violations of Rule 2: \n" + "========================================= \n";

      for (ArrayList<FinalBooking> list : bookings) {

        for (int i = 0; i < list.size() - 1; i++) {
          final FinalBooking bookingA = list.get(i);

          for (int j = i + 1; j < list.size(); j++) {
            final FinalBooking bookingB = list.get(j);

            if (noTimeConflict(bookingA, bookingB)) continue;

            if (bookingA.getRoom().equals(bookingB.getRoom())) {

              ruleA += bookingA.getWeekday() + " in room: " + bookingB.getRoom() + " between " + bookingA.getName() + " & " + bookingB.getName() + "\n";
            }

            if (violatesRule2(bookingA.getId(), bookingB.getId(), curricula)) {

              ruleB += bookingA.getWeekday() + " between " + bookingA.getName() + " & " + bookingB.getName() + "\n";
            }
          }
        }
      }

      System.out.println(ruleA);
      System.out.println(ruleB);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static int weekday (String day) {
    switch (day) {
      case "Mon":
        return 0;
      case "Tue":
        return 1;
      case "Wed":
        return 2;
      case "Thur":
        return 3;
      case "Fri":
        return 4;
    }
    return 5;
  }

  static boolean violatesRule2 (String lectureA, String lectureB, Map<String, ArrayList<String>> curricula) {
    ArrayList<String> curriculumA = curricula.get(lectureA);

    for (String sub : curriculumA) {
      if (curricula.get(lectureB).contains(sub)) return true;
    }
    return false;
  }

  static boolean noTimeConflict (FinalBooking bookingA, FinalBooking bookingB) {
    return bookingA.getStartTime().compare(bookingB.getEndTime()) >= 0 || bookingA.getEndTime().compare(bookingB.getStartTime()) <= 0;
  }
}