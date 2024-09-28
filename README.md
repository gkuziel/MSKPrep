# Overview and requirements

**The app:**
- Reads data from a JSON file.
- Displays the data in a list view screen.
- Allows navigation from each list item (event) to an editable detailed view screen.
- Allows data editing in the detailed view screen and reactively update the dataset and the UI 
- Contains both `Views(xml)` and `Jetpack Compose` implementation of the UI sharing as much business logic as possible

**The data consists of:**
- An array of `Event` instances.
- Each `Event` contains an array of `Result` instances.
- The relationship between `Event` and `Result` is 1-N

**`Event` Properties:**
- id - unique identifier for the event.
- desc - string describing the event.
- sync - boolean indicating if the event is synchronized with the backend (default is true).
- updated? - optional timestamp for the last update made to the event or its results (default is null = "never").
- validity? - optional integer representing how many seconds until the event decays:
    - 0 means the event has already decayed.
    - null means the event doesn't decay.

**`Result` Properties:**
- id - unique identifier for the result.
- desc - string describing the result.
- type - enum with values "AUTO" and "MANUAL" (case-insensitive).
- value? - optional integer representing the value of the result.

**App Functionality:**
- Upon first launch, the app should:
  - Read and deserialize the JSON data.
  - Display a list view of events.
- Non-decayed events should:
  - Be tappable, leading to a detail view screen.
  - Display only the "MANUAL" results (ignore "AUTO" results).
- In the detail view, users should:
  - Add new "MANUAL" results.
  - Modify the value of any existing result.

**UI Design Requirements:**
- Font color:
  - Black for non-decayed events.
  - Green for updated events.
  - Red for decayed events.
- Events should decay automatically:
  - Based on their validity value.
  - UI dynamically updates to red once an event decays (e.g., if validity = 10, the event turns red after 10 seconds).
- When a `Result` is added or updated:
  - The event’s validity resets to its initial value from the JSON.
  - The sync and updated properties should be updated.
  - The event should display in green until it decays again, then turn red.

# Solution

The solution is built using `Clean Architecture` principles combined with the `MVVM` design pattern. 

The project consists of 3 modules:
- `app_compose` - `Jetpack Compose` implementation of UI layer  
- `app_views` - `Views` implementation of UI layer  
- `core` - ViewModels and the remaining layers: domain + data (the data is exposed to the UI layer through the ViewModels flows)

also used: `Hilt`, `Flows`, `Coroutines`, `Gson`, `ViewModel`

# Demo:
<img src="https://github.com/user-attachments/assets/3ceb30f8-3b92-42d1-ae2e-efae5efac09c" width="300"/>
<img src="https://github.com/user-attachments/assets/5c1912be-d59d-4076-b7f3-1c386bc54e35" width="300"/>


# TODO:

1. Add tests (UI, snapshot, unit)
2. Decay timer refactor
3. Add Room db
4. Refactor connections between the layers (models types)
5. Add reload functionality


# License

```
   Copyright 2024 gkuziel
	
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
	
      http://www.apache.org/licenses/LICENSE-2.0
	
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
