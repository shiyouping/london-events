import classnames from "classnames";
import React, { PureComponent } from "react";
import {
  TabContent,
  TabPane,
  Nav,
  NavItem,
  NavLink,
  Container,
  Row,
  Col,
  Jumbotron,
  Pagination,
  PaginationItem,
  PaginationLink
} from "reactstrap";

import EventTab from "component/event-tab/event-tab";

const CATEGORY_MUSIC = "music";
const CATEGORY_CONFERENCE = "conference";
const CATEGORY_COMEDY = "comedy";
const CATEGORY_EDUCATION = "learning_education";
const CATEGORY_FESTIVALS = "festivals_parades";
const CATEGORY_FILM = "movies_film";
const CATEGORY_FOOD = "food";
const CATEGORY_HOLIDAY = "holiday";
const CATEGORY_BOOKS = "books";
const CATEGORY_ATTRACTIONS = "attractions";
const CATEGORY_COMMUNITY = "community";

export default class Event extends PureComponent {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.state = {
      activeTab: CATEGORY_MUSIC,
      events: [
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        },
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        },
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        },
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        },
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        },
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        },
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        },
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        },
        {
          imageUrl:
            "https://d1marr3m5x4iac.cloudfront.net/images/block250/I0-001/033/452/240-0.jpeg_/blink182-40.jpeg",
          title: "Blink-182 & Lil Wayne in Inglewood",
          startTime: "2019-07-05 05:15:44",
          venueAddress: "90 Wallis Road",
          weather: "Rain",
          city: "London"
        }
      ]
    };
  }

  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState({
        activeTab: tab
      });
    }
  }

  getNav() {
    return (
      <Nav tabs>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_MUSIC
            })}
            onClick={() => {
              this.toggle(CATEGORY_MUSIC);
            }}
          >
            Music
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_CONFERENCE
            })}
            onClick={() => {
              this.toggle(CATEGORY_CONFERENCE);
            }}
          >
            Conference
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_COMEDY
            })}
            onClick={() => {
              this.toggle(CATEGORY_COMEDY);
            }}
          >
            Comedy
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_EDUCATION
            })}
            onClick={() => {
              this.toggle(CATEGORY_EDUCATION);
            }}
          >
            Education
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_FESTIVALS
            })}
            onClick={() => {
              this.toggle(CATEGORY_FESTIVALS);
            }}
          >
            Festivals
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_FILM
            })}
            onClick={() => {
              this.toggle(CATEGORY_FILM);
            }}
          >
            Film
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_FOOD
            })}
            onClick={() => {
              this.toggle(CATEGORY_FOOD);
            }}
          >
            Food
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_HOLIDAY
            })}
            onClick={() => {
              this.toggle(CATEGORY_HOLIDAY);
            }}
          >
            Holiday
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_BOOKS
            })}
            onClick={() => {
              this.toggle(CATEGORY_BOOKS);
            }}
          >
            Books
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_ATTRACTIONS
            })}
            onClick={() => {
              this.toggle(CATEGORY_ATTRACTIONS);
            }}
          >
            Attractions
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_COMMUNITY
            })}
            onClick={() => {
              this.toggle(CATEGORY_COMMUNITY);
            }}
          >
            Community
          </NavLink>
        </NavItem>
      </Nav>
    );
  }

  getTabContent() {
    return (
      <TabContent activeTab={this.state.activeTab}>
        <TabPane tabId={CATEGORY_MUSIC}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_CONFERENCE}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_COMEDY}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_EDUCATION}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_FESTIVALS}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_FILM}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_FOOD}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_HOLIDAY}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_BOOKS}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_ATTRACTIONS}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_COMMUNITY}>
          <EventTab events={this.state.events} />
        </TabPane>
      </TabContent>
    );
  }

  getPagination() {
    return (
      <Row>
        <Col sm="12" md={{ size: 4, offset: 5 }}>
          <Pagination aria-label="Page navigation">
            <PaginationItem>
              <PaginationLink first href="#" />
            </PaginationItem>
            <PaginationItem>
              <PaginationLink previous href="#" />
            </PaginationItem>
            <PaginationItem>
              <PaginationLink next href="#" />
            </PaginationItem>
            <PaginationItem>
              <PaginationLink last href="#" />
            </PaginationItem>
          </Pagination>
        </Col>
      </Row>
    );
  }

  render() {
    return (
      <Container>
        <Jumbotron>
          <h1>London Events</h1>
          <p>Events and things to do in London.</p>
        </Jumbotron>
        {this.getNav()}
        {this.getTabContent()}
        {this.getPagination()}
      </Container>
    );
  }
}
