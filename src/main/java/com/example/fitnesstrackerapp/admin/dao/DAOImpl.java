package com.example.fitnesstrackerapp.admin.dao;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.dto.UserVitalDto;
import com.example.fitnesstrackerapp.admin.model.User;
import com.example.fitnesstrackerapp.admin.model.UserVital;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DAOImpl implements DAOInterface {
    private static final String RESOURCES_FOLDER = "src/main/resources/appdata/";
    private static final String USER_FILE = "UserDetails.txt";
    private static final String VITALS_FILE = "UserVitals.txt";
    private static final String USER_TYPE = "UserType";
    private static final String USERVITAL_TYPE = "UserVitalType";
    private List<User> userDetails = new ArrayList<>();
    private List<UserVital> userVitalDetails = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();


    public DAOImpl() {
        // Load existing users from the file or initialize an empty list
        this.userDetails = readObjectListFromJsonFile(USER_TYPE); //loadUserDetails();
        this.userVitalDetails = readObjectListFromJsonFile(USERVITAL_TYPE);
    }

    private Path getResourcesPath(String filename) {

        return Paths.get(RESOURCES_FOLDER + filename);
    }


    @Override
    public List addObject(Object object, String objectType) {
        if ("userType".equals(objectType)) {
            User user = new User();
            List<User> users = new ArrayList<>();

            UserDto userDto = (UserDto) object;

            user.setName(userDto.getName());
            Long newuserID = 1L;
            if (userDetails != null) {
                newuserID = userDetails.isEmpty() ? 1 : userDetails.getLast().getUserId() + 1;
            }
            // If user details is null then userid:0
            user.setUserId(newuserID);
            user.setPhone(userDto.getPhone());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            users.add(user);

            // Use the DAO to save the users
        /*DAOImpl daoImpl = new DAOImpl();
        userDetailsDAO.saveUsers(userDtos);*/

            userDetails.addAll(users);

            /*List<UserDto> userDtos = new ArrayList<>();
            for (User u : userDetails) {
                userDtos.add(new UserDto(u.getUserId(),u.getName(), u.getPhone(), u.getEmail(), u.getPassword()));
            }
            saveUserListToJsonFile(userDtos); // Save the updated list to the file
            return List.of(user);*/
            saveObjectListToJsonFile(userDetails, USER_TYPE);
            //saveUserListToJsonFile(userDetails);
            // Create the path for the text file in the resources folder
                /*Path filePath = getResourcesPath("UserDetails" + ".txt");

                // Write user data to the file
                Files.writeString(filePath, userDetails.toString());*/
            return userDetails;
        }
        if ("userVitalType".equals(objectType)) {
            UserVital userVital = new UserVital();
            List<UserVital> userVitals = new ArrayList<>();
            UserVitalDto userVitalDto = (UserVitalDto) object;

            User user = getObjbyEmail(userVitalDto.getEmail());
            if (user != null) {
                userVital.setUserId(user.getUserId());
                userVital.setAge(userVitalDto.getAge());
                userVital.setHeight(userVitalDto.getHeight());
                userVital.setCurrent_weight(userVitalDto.getCurrent_weight());
                userVital.setTarget_weight(userVitalDto.getTarget_weight());
                userVitals.add(userVital);

                userVitalDetails.addAll(userVitals);
                try {
                    Path filePath = getResourcesPath("UserVitals" + ".txt");
                    Files.writeString(filePath, userVitalDetails.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

            } else {
                return null;
            }


        }

        return List.of();
    }

    @Override
    public List updateObjectListToJsonFile(UserDto userDto, String objectType) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (USER_TYPE.equals(objectType)) {
            List<User> users = userDetails;
            try {
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    if (user != null && user.getEmail().equals(userDto.getEmail())) {
                        // Update the existing user directly
                        if (userDto.getPassword() != null) {
                            user.setPassword(userDto.getPassword());
                        }
                        if (userDto.getName() != null) {
                            user.setName(userDto.getName());
                        }
                        user.setUserId(user.getUserId());
                        user.setPhone(user.getPhone());
                        user.setEmail(user.getEmail());
                    }
                }

                /*for (User user : users) {
                    if (user != null && user.getEmail().equals(userDto.getEmail())) {
                        User updatedUser = new User();

                        if (userDto.getPassword() != null) {
                            updatedUser.setPassword(userDto.getPassword());
                        }

                        if (userDto.getName() != null) {
                            updatedUser.setName(userDto.getName());
                        }
                        updatedUser.setUserId(user.getUserId());
                        updatedUser.setPhone(user.getPhone());
                        updatedUser.setEmail(userDto.getEmail());
                        users.add(updatedUser);
                    } else {
                        users.add(user);
                    }
                }*/

                // Convert List<UserDto> to JSON string
                String json = objectMapper.writeValueAsString(users) == null ? "" : objectMapper.writeValueAsString(users);

                // Save JSON to UserFile.txt
                try (FileWriter writer = new FileWriter(String.valueOf(getResourcesPath(USER_FILE)))) {
                    writer.write(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return users;

        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List getAll() {
        return userDetails;
    }

    @Override
    public String add(List object) {
        return "";
    }

    @Override
    public Object getObj(Long id) {
        if (userDetails != null && !userDetails.isEmpty()) {
            for (User user : userDetails) {
                if (user.getUserId().equals(id)) {
                    return user; // Return the matching User object
                }
            }
        }
        return null; // Return null if no match is found
    }

    @Override
    public UserVital getObjVital(Long id) {
        if (userVitalDetails != null && !userVitalDetails.isEmpty()) {
            for (UserVital userVital : userVitalDetails) {
                if (userVital.getUserId().equals(id)) {
                    return userVital; // Return the matching User object
                }
            }
        }
        return null; // Return null if no match is found
    }

    @Override
    public User getObjbyEmail(String email) {
        if (userDetails != null && !userDetails.isEmpty()) {
            for (User user : userDetails) {
                if (user.getEmail().equals(email)) {
                    return user; // Return the matching User object
                }
            }
        }
        return null;
    }


    /*public List<User> loadUserDetails() {
        try {
            Path path = Paths.get(RESOURCES_FOLDER + "UserDetails.txt");
            if (Files.exists(path)) {
                String jsonContent = Files.readString(path);
                return parseJsonToList(jsonContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public List<UserVital> loadUserVitalDetails() {
        try {
            Path path = Paths.get(RESOURCES_FOLDER + "UserVitals.txt");
            if (Files.exists(path)) {
                String jsonContent = Files.readString(path);
                if (jsonContent != null) {
                    jsonContent= jsonContent.replace("\n","").replace("\r","");
                }
                UserVital[] dtoArray = objectMapper.readValue(jsonContent, UserVital[].class);
                List<UserVital> userVitals = Arrays.asList(dtoArray);
                //List<UserVital> userVitals = objectMapper.readValue(jsonContent, new TypeReference<List<UserVital>>() {});
                return userVitals;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }*/

    @Override
    public List saveObjectListToJsonFile(List objects, String objectType) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (USER_TYPE.equals(objectType)) {
            List<User> users = new ArrayList<>();
            try {
                for (Object obj : objects) {
                    if (obj instanceof User) {
                        users.add((User) obj);
                    } else {
                        throw new IllegalArgumentException("List contains non-User objects");
                    }
                }

                // Convert List<UserDto> to JSON string
                String json = objectMapper.writeValueAsString(users) == null ? "" : objectMapper.writeValueAsString(users);

                // Save JSON to UserFile.txt
                try (FileWriter writer = new FileWriter(String.valueOf(getResourcesPath(USER_FILE)))) {
                    writer.write(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return users;
        } else if (USERVITAL_TYPE.equals(objectType)) {
            List<UserVital> userVitals = new ArrayList<>();
            try {
                userVitals = Arrays.asList((UserVital[]) objects.toArray());

                // Convert List<UserDto> to JSON string
                String json = objectMapper.writeValueAsString(userVitals) == null ? "" : objectMapper.writeValueAsString(userVitals);

                // Save JSON to UserFile.txt
                try (FileWriter writer = new FileWriter(String.valueOf(getResourcesPath(VITALS_FILE)))) {
                    writer.write(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return userVitals;
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List readObjectListFromJsonFile(String objectType) {
        if (USER_TYPE.equals(objectType)) {
            List<User> users = new ArrayList<>();
            try {
                // Read JSON string from UserFile.txt
                String json = new String(Files.readAllBytes(Paths.get(String.valueOf(getResourcesPath(USER_FILE)))));

                // Convert JSON string to List<UserDto>
                users = objectMapper.readValue(json, new TypeReference<List<User>>() {
                });

            } catch (IOException e) {
                e.printStackTrace();

            }
            return users;
        } else if (USERVITAL_TYPE.equals(objectType)) {
            List<UserVital> userVitals = new ArrayList<>();
            try {
                // Read JSON string from UserFile.txt
                String json = new String(Files.readAllBytes(Paths.get(String.valueOf(getResourcesPath(VITALS_FILE)))));

                // Convert JSON string to List<UserDto>
                userVitals = objectMapper.readValue(json, new TypeReference<List<UserVital>>() {
                });

            } catch (IOException e) {
                e.printStackTrace();

            }
            return userVitals;

        } else {
            return new ArrayList<>();
        }

    }
    /*private List<User> parseJsonToList(String jsonContent) {
        // Manually parse JSON (you can use libraries like Jackson or Gson for better parsing)
        List<User> list = new ArrayList<>();
        if (jsonContent.startsWith("[") && jsonContent.endsWith("]")) {
            String[] users = jsonContent.substring(1, jsonContent.length() - 1).split("\\}, \\{");
            for (String user : users) {
                String cleanedUser = user.replaceAll("[\\[\\]\\{\\}]", "");
                String[] fields = cleanedUser.split(", ");
                Long id = Long.parseLong(fields[0].split(": ")[1]);
                String name = fields[1].split(": ")[1].replace("\"", "");
                String phone = fields[2].split(": ")[1].replace("\"", "");
                String email = fields[3].split(": ")[1].replace("\"", "");
                String password = fields[4].split(": ")[1].replace("\"", "");
                list.add(new User(id, name, phone, email, password));
            }
        }
        return list;
    }*/
    /*private List<UserVital> parseJsonToListforvital(String jsonContent) {
        // Manually parse JSON (you can use libraries like Jackson or Gson for better parsing)
        List<UserVital> list = new ArrayList<>();
        if (jsonContent.startsWith("[") && jsonContent.endsWith("]")) {
            String[] users = jsonContent.substring(1, jsonContent.length() - 1).split("\\}, \\{");
            for (String user : users) {
                String cleanedUser = user.replaceAll("[\\[\\]\\{\\}]", "");
                String[] fields = cleanedUser.split(", ");
                Long userid = Long.parseLong(fields[0].split(": ")[1]);
                String age = fields[1].split(": ")[1].replace("\"", "");
                String height = fields[2].split(": ")[1].replace("\"", "");
                String current_weight = fields[3].split(": ")[1].replace("\"", "");
                String target_weight = fields[4].split(": ")[1].replace("\"", "");
                list.add(new UserVital(userid,age, height, current_weight, target_weight));
            }
        }
        return list;
    }*/
}
