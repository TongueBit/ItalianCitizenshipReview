<a name="readme-top"></a>
<br/>
<div align="center">
  <a href="https://www.italiancitizenshipreview.com/index">
    <img src="https://www.italiancitizenshipreview.com/images/logo.png" alt="Logo" width="350" height="350">
  </a>

<h3 align="center">Italian Citizenship Review</h3>

  <p align="center">
Review application to help those seeking Italain citizenship service providers.
      <br />
Full stack java web app, using Thymeleaf, Spring-Boot, Spring Security, Hibernate/JPA and MySQL.
    <br />
    <a href="https://www.italiancitizenshipreview.com/index"><strong>View Deployment</strong></a>
    <br />
    <br />
    <a href="https://github.com/TongueBit/ItalianCitizenshipReview/issues">Report Bug</a>
    ·
    <a href="https://github.com/TongueBit/ItalianCitizenshipReview/issuess">Request Feature</a>
  </p>
</div>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

## About The Project

<a href="https://github.com/TongueBit/ItalianCitizenshipReview/issues">Italian Citizenship Review</a>
</br>
This app was created because of the 400 plus million people around the world eliible for Italian citizenship by ancestry, there is little transparency in the service providers who help facilitate the aqcuiring of citizenship.

I built the app with Java (SpringBoot, Hibernate, Thymeleaf, Maven) and MySQL. The front-end was built using Bootstrap. I implemented Spring Security as well. 

The app will continue to evolve, feedback, suggestions and bug reports are welcome.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

<a href="https://www.java.com">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="50" height="50" /></a> 
<a href="https://spring.io/projects/spring-boot">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="50" height="50" /></a> 
<a href="https://www.mysql.com/">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="50" height="50" /></a> 
<a href="https://developer.mozilla.org/en-US/docs/Web/HTML">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-plain-wordmark.svg" width="50" height="50" /></a> 
<a href="https://developer.mozilla.org/en-US/docs/Web/CSS">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-plain-wordmark.svg" width="50" height="50" /></a> 
<a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg" width="50" height="50" /></a> 
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

You can visit <a href="http://italiancitizenshipreview.com/">Italian Citizenship Review</a> to try the app online.

The homepage explains that this website is for finding service-providers for becoming an italian citizen with the best reputation.

You must register in order to leave a review. You will be sent a verification email. 

Once registered, you can leave a review for any service-provider. If the one you want doesn't exist, you can add a new one in the user dashboard section. All new service providers must be approved by an admin.


### Prerequisites

To run locally, you will need an IDE with Java 17 or higher.

You will also need a MySQL localhost on port 3306, and will need to edit the application.properties file to access your
localhost:
i.e.: "spring.datasource.username=" and "spring.datasource.password=" will need correct credentials for your localhost.

The app will automatically create a database at "spring.datasource.url=jdbc:mysql://localhost:3306/italiancitizenshipreview", unless
you create one named "italiancitizenshipreview" manually.

### Installation

1. Clone the repo
   ```sh
   git clone git@github.com:TongueBit/ItalianCitizenshipReview.git
   ```
2. Import the repo to your IDE
3. Build and run the application
4. Open your web browser and go to:

  ```sh
   http://127.0.0.1:8080/
   ```

5. This will lead you to the homepage.
6. Register an account then login to leave a review.
7. If you want to create an admin, use sql to change ROLE to ROLE_ADMIN

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage



This project could also be forked and adapted to use for different review sites with relatively little changes needed.
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Roadmap

- [ ] Use Google Places API to get existing reviews for service-providers on Google.
- [ ] Improve UI, especially for smaller screens.
- [ ] Add content related to becoming an Italian Citizen.
- [ ] Use Afiliated marketing to aid in the travel planning sometimes needed in order to become an Italian Citizen.
    - [ ] Travel agencies...
    - [ ] Travel insurance
- [ ] Do some data analytics
    - [ ] Best communues to apply
    - [ ] consulate wait times
    - [ ] Other fun stuff


See the [issues](https://github.com/TongueBit/ItalianCitizenshipReview/issues) to request features and see known
issues.
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also
simply open an issue with the tag "enhancement".

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/NewFeature`)
3. Commit your Changes (`git commit -m 'Add some NewFeature'`)
4. Push to the Branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## License

Distributed under the GNU General Public License. See `LICENSE` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contact

Tony Edmonds - [@linkedin](https://www.linkedin.com/in/edmonds-tony/) - tonyledmonds@gmail.com

Project Link: [https://github.com/TongueBit/ItalianCitizenshipReview/](https://github.com/TongueBit/ItalianCitizenshipReview/)
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

* Coders Campus Java Boot Camp

<p align="right">(<a href="#readme-top">back to top</a>)</p>
