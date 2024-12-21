package com.example.fitnesstrackerapp.admin.dao;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.model.User;

import java.util.List;

public interface DAOInterface<T> {


    List<T> addObject(Object object, String objectType);

    List updateObjectListToJsonFile(UserDto userDto, String objectType);

    List<T> getAll();
    String add(List<T> object);
    T getObj(Long id);

    Object getObjVital(Long id);

    User getObjbyEmail(String email);

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
    List saveObjectListToJsonFile(List objects, String objectType);

    List readObjectListFromJsonFile(String objectType);
}
